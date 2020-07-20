package com.example.currencyconvertor

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.currencyconvertor.framework_layout.db.AppDb
import com.example.currencyconvertor.framework_layout.network.FakeHttpRequest
import com.example.currencyconvertor.interface_layout.repository.RepositoryImpl
import com.example.currencyconvertor.interface_layout.utility.TestData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class RequestTest {
    private lateinit var repository: RepositoryImpl
    private lateinit var db: AppDb

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDb::class.java).build()
        repository = RepositoryImpl(db)
        repository.saveCache(TestData.getCachedTemplateCurrencies())
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun httpRequestTestSuccess() {
        val req = FakeHttpRequest(FakeHttpRequest.SUCCESS)
        runBlocking {
            val job = async{
                repository.getCurrencies(req)
            }
            val data = job.await()
            assertEquals(3, data.size)
            assertEquals("USD", data[0].CharCode)
            assertEquals(66.1835f, data[2].Value)
        }
    }

    @Test
    fun httpRequestTestTimeout() {
        val req = FakeHttpRequest(FakeHttpRequest.TIMEOUT)
        runBlocking {
            val job = async{
                repository.getCurrencies(req)
            }
            val data = job.await()
            assertEquals(3, data.size)
            assertEquals(67.1835f, data[2].Value)
        }
    }

    @Test
    fun httpRequestTestEmptyResponse() {
        val req = FakeHttpRequest(FakeHttpRequest.EMPTY_RESPONSE)
        runBlocking {
            val job = async{
                repository.getCurrencies(req)
            }
            val data = job.await()
            assertEquals(3, data.size)
            assertEquals(81.8392f, data[1].Value)
        }
    }


    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.currencyconvertor", appContext.packageName)
    }
}
