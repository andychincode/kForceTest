package com.kforce.test.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kforce.test.data.AcronymRepository
import com.kforce.test.viewmodel.AcronymsViewModel

class ViewModelFactory(private val repository: AcronymRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AcronymsViewModel(repository) as T
    }
}
