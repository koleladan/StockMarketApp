package com.example.stockmarketapp.stockmarkets.domain.model

import java.time.LocalDate
import java.time.LocalDateTime

data class IntradayInfo(
    val date: LocalDateTime,
    val close: Double
)
