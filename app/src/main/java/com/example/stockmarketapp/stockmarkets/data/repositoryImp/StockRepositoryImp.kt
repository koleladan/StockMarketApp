package com.example.stockmarketapp.stockmarkets.data.repositoryImp

import com.example.stockmarketapp.core.util.Resource
import com.example.stockmarketapp.stockmarkets.data.local.StockDatabase
import com.example.stockmarketapp.stockmarkets.data.local.mapper.toCompanyListing
import com.example.stockmarketapp.stockmarkets.data.remote.dto.StockApi
import com.example.stockmarketapp.stockmarkets.domain.model.CompanyListing
import com.example.stockmarketapp.stockmarkets.domain.repository.StockRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImp @Inject constructor(
    val api: StockApi,
    val db: StockDatabase

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
              response.byteStream()

          } catch (e: IOException){
              e.printStackTrace()
              emit(Resource.Error( "An error occured"))

          }catch (e: Exception){
              e.printStackTrace()
              emit(Resource.Error("Could not access Internet"))

          }
      }
    }
}

//then create a csv package in the data package
// Inside the csv package have an interface CSVParser and a parser class that will implement interface parser