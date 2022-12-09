package com.kforce.test.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kforce.test.data.AcronymRepository
import com.kforce.test.data.FetchAcronymsCallback
import com.kforce.test.model.AcronymData

class AcronymsViewModel(private val repository: AcronymRepository) : ViewModel() {
    private val _acronyms = MutableLiveData<List<AcronymData>>()
    val acronyms: LiveData<List<AcronymData>> = _acronyms

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun loadAcronyms(sf: String) {
        repository.fetchAcronyms(sf, object : FetchAcronymsCallback<List<AcronymData>> {
            override fun onSuccess(data: List<AcronymData>?) {
                data.let {
                    _acronyms.postValue(it)
                }
            }

            override fun onError(error: String?) {
                _error.postValue(error)
            }
        })
    }
}