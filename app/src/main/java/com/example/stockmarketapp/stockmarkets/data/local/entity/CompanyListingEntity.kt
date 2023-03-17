package com.example.stockmarketapp.stockmarkets.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CompanyListingEntity(
    val name:String,
    val symbol:String,
    val exchange:String,
    @PrimaryKey  val id: Int? =null,


)
// after the entity create the data class in model domain package

