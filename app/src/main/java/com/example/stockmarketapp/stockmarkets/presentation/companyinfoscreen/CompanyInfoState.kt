package com.example.stockmarketapp.stockmarkets.presentation.companyinfoscreen

import com.example.stockmarketapp.stockmarkets.domain.model.CompanyInfo
import com.example.stockmarketapp.stockmarkets.domain.model.IntradayInfo

data class CompanyInfoState(
    val stockInfos: List<IntradayInfo> = emptyList(),
    val company: CompanyInfo? = null,
    val isLoading:Boolean = false,
    val error:String? = null

)
