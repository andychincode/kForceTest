package com.kforce.test.data

import com.kforce.test.model.AcronymData
import com.kforce.test.network.ApiClient
import io.reactivex.rxjava3.core.Observable

class AcronymRemoteDataSource(apiClient: ApiClient): AcronymDataSource {
    private lateinit var fetchAcronymsCall: Observable<List<AcronymData>>

    private val service = apiClient.build()

    override fun retrieveAcronyms(sf: String, callback: FetchAcronymsCallback<List<AcronymData>>) {
        fetchAcronymsCall = service.getAcronyms(sf)
        fetchAcronymsCall.subscribe(
            { value -> callback.onSuccess(value) },
            { error -> callback.onError(error.message) }
        )
    }
}