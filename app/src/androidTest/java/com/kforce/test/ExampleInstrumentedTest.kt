package com.kforce.test

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kforce.test.data.AcronymDataSource
import com.kforce.test.data.AcronymRemoteDataSource
import com.kforce.test.data.AcronymRepository
import com.kforce.test.data.FetchAcronymsCallback
import com.kforce.test.model.AcronymData
import com.kforce.test.network.ApiClient

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.kforce.test", appContext.packageName)
    }

    @Test
    fun testLoadAcronym() {
        val acronymDataSource: AcronymDataSource = AcronymRemoteDataSource(ApiClient)
        val repository = AcronymRepository(acronymDataSource)

        val testAcronym = "ASAP"
        repository.fetchAcronyms(testAcronym, object : FetchAcronymsCallback<List<AcronymData>> {
            override fun onSuccess(data: List<AcronymData>?) {
            }

            override fun onError(error: String?) {
            }
        })
    }
}