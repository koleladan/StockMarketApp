package com.example.stockmarketapp.stockmarkets.data.remote.dto

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json

@JsonSerializable
data class CompanyInfoDto(
    @field:Json(name = "Symbol") val symbol:String?,
    @field:Json(name = "Description") val description:String?,
    @field:Json(name = "Name") val name:String?,
    @field:Json(name ="Country") val country: String?,
    @field:Json(name = "Industry") val industry:String?,
    //annotated with @field:Json(name = "") because it is not used exactly as documented in Api ie Cases are different
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(symbol)
        parcel.writeString(description)
        parcel.writeString(name)
        parcel.writeString(country)
        parcel.writeString(industry)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CompanyInfoDto> {
        override fun createFromParcel(parcel: Parcel): CompanyInfoDto {
            return CompanyInfoDto(parcel)
        }

        override fun newArray(size: Int): Array<CompanyInfoDto?> {
            return arrayOfNulls(size)
        }
    }
}

annotation class JsonSerializable
