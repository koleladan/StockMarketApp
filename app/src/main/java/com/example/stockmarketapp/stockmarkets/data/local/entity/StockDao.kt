package com.example.stockmarketapp.stockmarkets.data.local.entity

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

// explains how we want to interact with the Database
@Dao
interface StockDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompanyListings(
        CompanyListingEntities: List<CompanyListingEntity>
    )

    @Query("DELETE FROM companylistingentity")
    suspend fun clearCompanyListings()

    @Query( """ SELECT *
                FROM companylistingentity
                WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%' OR
                UPPER(:query) == symbol
                
                
        
        
    """

    )
    // on  this query; the query/search will be converted by sql to lowercase and put in between the % sign to check on the matching names
    // or it will be converted  by sql to uppercase and put in between the % signs to check on the matching symbol
    // for instance the query is ToTl. Its converted to %total% to match any name in the list or %TOTAL% to match any symbol in the list
    suspend fun  searchCompanyListing(query:String): List<CompanyListingEntity>


}

// next implement the local database which is an abstract class in local package