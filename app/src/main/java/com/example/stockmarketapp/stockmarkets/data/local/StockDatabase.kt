package com.example.stockmarketapp.stockmarkets.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.stockmarketapp.stockmarkets.data.local.entity.CompanyListingEntity
import com.example.stockmarketapp.stockmarkets.data.local.entity.StockDao

@Database(
 entities =[CompanyListingEntity::class],
 version = 1

)
abstract class StockDatabase:RoomDatabase() {
 abstract val dao:StockDao
}