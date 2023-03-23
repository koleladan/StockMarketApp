package com.example.stockmarketapp.stockmarkets.data.remote.dto

import com.squareup.moshi.Json

data class CompanyInfoDto(
    @field:Json(name = "Symbol") val symbol:String?,
    @field:Json(name = "Description") val description:String?,
    @field:Json(name = "Name") val name:String?,
    @field:Json(name ="Country") val country: String?,
    @field:Json(name = "Industry") val industry:String?,
    //annotated with @field:Json(name = "") because it is not used exactly as documented in Api ie Cases are different
)
