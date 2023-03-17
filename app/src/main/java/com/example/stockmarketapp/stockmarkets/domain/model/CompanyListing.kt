package com.example.stockmarketapp.stockmarkets.domain.model

data class CompanyListing(
    val name:String,
    val symbol:String,
    val exchange:String,
)
//// we therefore create a mapper that will help in transforming the CompanyListingEntity to the companyListing in the domain package