package com.example.stockmarketapp.stockmarkets.domain.repository

import com.example.stockmarketapp.core.util.Resource
import com.example.stockmarketapp.stockmarkets.domain.model.CompanyListing
import kotlinx.coroutines.flow.Flow

interface StockRepository {
    suspend fun getCompanyListings(
        //create the util domain in the core domain
    //in the util have a sealed class Resource
    //then;
    fetchFromRemote: Boolean,
    query: String

    ): Flow<Resource<List<CompanyListing>>>
}
//Then write the  repository implement in the repositoryImp package in the data package