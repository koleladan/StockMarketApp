package com.example.stockmarketapp.stockmarkets.presentation.companiesscreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockmarketapp.core.util.Resource
import com.example.stockmarketapp.stockmarkets.domain.repository.StockRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CompaniesViewModel  @Inject constructor(
    private  val repository: StockRepository
): ViewModel(){
    var  state by mutableStateOf(CompaniesState())

    private var searchJob: Job? = null

    init {
        getCompanies()
    }

    fun onEvent(event: CompaniesEvent){
        when(event){
            is CompaniesEvent.Refresh ->{
                getCompanies(fetchFromRemote = true)

            }
            is CompaniesEvent.OnSearchQueryChange ->{
                state = state.copy(searchQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                    getCompanies()
                }


            }
        }
    }
    fun  getCompanies(
        query:String = state.searchQuery.lowercase(),
        fetchFromRemote:Boolean =false

    ){
        viewModelScope.launch {
            repository
                .getCompanyListings(fetchFromRemote, query)
                .collect{result ->
                    when(result){
                        is Resource.Success ->{
                            result.data?.let { listings ->
                                state = state.copy(
                                    companies = listings
                                )

                            }

                        }
                        is Resource.Error -> Unit
                        is Resource.Loading ->{
                            state = state.copy(isLoading = result.isLoading)

                        }
                    }
                }

        }
    }
}