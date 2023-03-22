package com.example.stockmarketapp.stockmarkets.di

import com.example.stockmarketapp.stockmarkets.data.cvs.CSVParser
import com.example.stockmarketapp.stockmarkets.data.cvs.CompanyListingsParser
import com.example.stockmarketapp.stockmarkets.data.repositoryImp.StockRepositoryImp
import com.example.stockmarketapp.stockmarkets.domain.model.CompanyListing
import com.example.stockmarketapp.stockmarkets.domain.repository.StockRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCompanyListingsParser(
        companyListingsParser: CompanyListingsParser
    ):CSVParser<CompanyListing>

    @Binds
    @Singleton
    abstract fun  bindStockRepository(
        stockRepositoryImp: StockRepositoryImp
    ):StockRepository

}