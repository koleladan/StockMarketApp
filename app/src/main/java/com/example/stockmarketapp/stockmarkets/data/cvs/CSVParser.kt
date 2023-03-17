package com.example.stockmarketapp.stockmarkets.data.cvs

import java.io.InputStream
import java.io.InputStreamReader

interface CSVParser <T> {
    suspend fun  parse(stream: InputStream): List<T>

}