package com.kforce.test.data

import com.kforce.test.model.AcronymData

class AcronymRepository(private val acronymDataSource: AcronymDataSource) {
    fun fetchAcronyms(sf: String, callback: FetchAcronymsCallback<List<AcronymData>>) {
        acronymDataSource.retrieveAcronyms(sf, callback)
    }
}