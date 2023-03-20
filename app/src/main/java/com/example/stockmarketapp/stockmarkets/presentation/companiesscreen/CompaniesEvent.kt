package com.example.stockmarketapp.stockmarkets.presentation.companiesscreen

import retrofit2.http.Query

sealed class CompaniesEvent {
    object Refresh: CompaniesEvent()
    data class OnSearchQueryChange(val query: String): CompaniesEvent()
}
