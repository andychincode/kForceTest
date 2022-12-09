package com.kforce.test.di

import androidx.lifecycle.ViewModelProvider
import com.kforce.test.data.AcronymDataSource
import com.kforce.test.data.AcronymRemoteDataSource
import com.kforce.test.data.AcronymRepository
import com.kforce.test.network.ApiClient

object Injection {
    private val acronymDataSource: AcronymDataSource = AcronymRemoteDataSource(ApiClient)
    private val acronymRepository = AcronymRepository(acronymDataSource)
    private val acronymViewModelFactory = ViewModelFactory(acronymRepository)

    fun provideViewModelFactory(): ViewModelProvider.Factory {
        return acronymViewModelFactory
    }
}