package com.example.stockmarketapp.stockmarkets.data.remote.dto

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface StockApi {
    @GET ("query?function=LISTING_STATUS")
    suspend fun getListings(
        @Query("apikey") apikey:String = API_KEY
    ) : ResponseBody


    companion object{
        const val API_KEY = "AVPV124IZCAU5HN2"
        const val BASE_URL ="https://www.alphavantage.co"
    }
}

//starting point
//Then create the Entity in the local data package