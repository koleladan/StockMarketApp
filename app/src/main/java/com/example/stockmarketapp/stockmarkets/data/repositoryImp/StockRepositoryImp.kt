package com.example.stockmarketapp.stockmarkets.data.repositoryImp

import com.example.stockmarketapp.core.util.Resource
import com.example.stockmarketapp.stockmarkets.data.cvs.CSVParser
import com.example.stockmarketapp.stockmarkets.data.cvs.CompanyListingsParser
import com.example.stockmarketapp.stockmarkets.data.local.StockDatabase
import com.example.stockmarketapp.stockmarkets.data.local.mapper.toCompanyInfo
import com.example.stockmarketapp.stockmarkets.data.local.mapper.toCompanyListing
import com.example.stockmarketapp.stockmarkets.data.local.mapper.toCompanyListingEntity
import com.example.stockmarketapp.stockmarkets.data.remote.dto.StockApi
import com.example.stockmarketapp.stockmarkets.domain.model.CompanyInfo
import com.example.stockmarketapp.stockmarkets.domain.model.CompanyListing
import com.example.stockmarketapp.stockmarkets.domain.model.IntradayInfo
import com.example.stockmarketapp.stockmarkets.domain.repository.StockRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImp @Inject constructor(
    private val api: StockApi,
     private val db: StockDatabase,
   private val companyListingsParser: CSVParser<CompanyListing>,
    private val intradayInfoParser: CSVParser<IntradayInfo>

): StockRepository {

    private val dao = db.dao


    override suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>> {
      return flow {
          emit(Resource.Loading(true))
          val localListings = dao.searchCompanyListing(query)

          emit(Resource.Success(
              data = localListings.map { it.toCompanyListing() }
          ))
          val isDbEmpty = localListings.isEmpty() && query.isBlank()
          val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote
          if (shouldJustLoadFromCache){
              emit(Resource.Loading(false))
              return@flow

          }
          val remoteListings = try {
              val response = api.getListings()
              companyListingsParser.parse(response.byteStream())


          } catch (e: IOException){
              e.printStackTrace()
              emit(Resource.Error( "An error occured"))
              null
              //parse null because we don't have remote data

          }catch (e: Exception){
              e.printStackTrace()
              emit(Resource.Error("Could not access Internet"))
              null
              //parse null because we don't have remote data

          }
          //sticking to single source of truth values where data from api call is first loaded to the local database; cache
          // then data on the UI is retrieved from the single source (local database: cache); data on the UI is not directly from the api call
          remoteListings?.let {  listings ->
              dao.clearCompanyListings()
              dao.insertCompanyListings(
                  listings.map { it.toCompanyListingEntity() }
              )
              emit(Resource.Success(
                  data =dao
                      .searchCompanyListing("")
                      .map { it.toCompanyListing() }
              ))
              emit(Resource.Loading(false))
          }

      }
    }

    override suspend fun getIntradayInfo(symbol: String): Resource<List<IntradayInfo>> {
        return try {
            val response = api.getIntradayInfo(symbol)
           val results = intradayInfoParser.parse(response.byteStream())
            Resource.Success(results)

        } catch (e: IOException){
            e.printStackTrace()
            Resource.Error(
                message = "Could not load intraday info"
            )
        }catch (e:HttpException){
            e.printStackTrace()
            Resource.Error(
                message = "Could not load intraday info"
            )
        }
    }

    override suspend fun getCompanyInfo(symbol: String): Resource<CompanyInfo> {
       return  try {
           val result = api.getCompanyInfo(symbol)
           Resource.Success(result.toCompanyInfo()) // no need for Csv mapper

       } catch (e:IOException){
           e.printStackTrace()
           Resource.Error(
               message = "Could not load company information"
           )
       }catch (e:HttpException){
           e.printStackTrace()
           Resource.Error(
               message = "Could not load company Info"
           )
       }
    }
}

//then create a csv package in the data package
// Inside the csv package have an interface CSVParser and a parser class that will implement interface parser