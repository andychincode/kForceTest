package com.kforce.test.data

import com.kforce.test.model.AcronymData

interface AcronymDataSource {
    fun retrieveAcronyms(sf: String, callback: FetchAcronymsCallback<List<AcronymData>>)
}