package com.example.stockmarketapp.stockmarkets.data.remote.dto

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json


data class CompanyInfoDto(
    @Json(name = "Symbol") val symbol:String?,
    @Json(name = "Description") val description:String?,
    @Json(name = "Name") val name:String?,
    @Json(name ="Country") val country: String?,
    @Json(name = "Industry") val industry:String?,
    //annotated with @field:Json(name = "") because it is not used exactly as documented in Api ie Cases are different
)
