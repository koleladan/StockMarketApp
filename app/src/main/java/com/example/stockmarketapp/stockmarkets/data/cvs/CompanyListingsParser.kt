package com.example.stockmarketapp.stockmarkets.data.cvs

import com.example.stockmarketapp.stockmarkets.domain.model.CompanyListing
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.InputStreamReader
import javax.inject.Inject
import javax.inject.Singleton


//@Singleton
//class CompanyListingsParser @Inject constructor():CSVParser<CompanyListing> {
   // override suspend fun  parse(stream: InputStream): List<CompanyListing> {
       //val csvReader = CSVReader(InputStreamReader(stream))
       // return  withContext(Dispatchers.IO){
       //     csvReader
           //     .readAll()
              //  .drop(1)
              //  .mapNotNull
     //   }


   // }
//}