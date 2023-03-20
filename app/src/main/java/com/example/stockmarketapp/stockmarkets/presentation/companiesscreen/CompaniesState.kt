package com.example.stockmarketapp.stockmarkets.presentation.companiesscreen

import com.example.stockmarketapp.stockmarkets.domain.model.CompanyListing
import retrofit2.http.Query

data class CompaniesState(
    val companies: List<CompanyListing> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = ""

)
//create a sealed class for different UI events ie clicking on an item