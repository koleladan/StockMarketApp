package com.example.stockmarketapp.stockmarkets.data.local.mapper

import com.example.stockmarketapp.stockmarkets.data.remote.dto.CompanyInfoDto
import com.example.stockmarketapp.stockmarkets.domain.model.CompanyInfo

fun CompanyInfoDto.toCompanyInfo():CompanyInfo{
    return CompanyInfo(
        symbol = symbol?: "",
        description = description?: "",
        name = name?: "",
        country = country?: "",
        industry = industry?: ""

    )

}
//after the mappers add an api request