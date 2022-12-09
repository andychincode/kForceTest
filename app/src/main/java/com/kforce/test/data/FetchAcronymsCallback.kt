package com.kforce.test.data

interface FetchAcronymsCallback<T> {
    fun onSuccess(data: T?)
    fun onError(error: String?)
}