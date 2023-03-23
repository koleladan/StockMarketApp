package com.example.stockmarketapp.stockmarkets.data.local.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.text.intl.Locale
import com.example.stockmarketapp.stockmarkets.data.remote.dto.IntradayInfoDto
import com.example.stockmarketapp.stockmarkets.domain.model.IntradayInfo
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun IntradayInfoDto.toIntradayInfo(): IntradayInfo{
    val pattern = "yyyy-MM-dd  HH:mm:ss"
    val formatter = DateTimeFormatter.ofPattern(pattern, java.util.Locale.getDefault())
    val localDateTime = LocalDateTime.parse(timestamp, formatter)
    return IntradayInfo(
        date = localDateTime,
        close = close

    )

}