package com.example.stockmarketapp.stockmarkets.domain.model

import com.squareup.moshi.Json

data class CompanyInfo(
    val symbol:String,
    val description:String,
    val country:String,
     val industry:String,
    val name:String,
)
