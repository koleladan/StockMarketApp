package com.example.stockmarketapp.stockmarkets.data.local.mapper

import com.example.stockmarketapp.stockmarkets.data.local.entity.CompanyListingEntity
import com.example.stockmarketapp.stockmarkets.domain.model.CompanyListing

fun CompanyListingEntity.toCompanyListing(): CompanyListing{
    return CompanyListing(
        name = name,
        symbol = symbol,
        exchange = exchange,
    )
}

fun CompanyListing.toCompanyListingEntity(): CompanyListingEntity{
    return CompanyListingEntity(
        name = name,
        symbol = symbol,
        exchange = exchange,
    )
}

//Then define the Database Access Object(Dao)