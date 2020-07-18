package com.example.currencyconvertor

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.currencyconvertor.framework_layout.db.AppDb
import com.example.currencyconvertor.framework_layout.db.CurrencyDao
import com.example.currencyconvertor.framework_layout.db.CurrencyEntity
import com.example.currencyconvertor.interface_layout.repository.RepositoryImpl
import com.example.currencyconvertor.interface_layout.utility.TemplateData
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class SQLTest {

    private lateinit var dao: CurrencyDao
    private lateinit var repository: RepositoryImpl
    private lateinit var db: AppDb

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDb::class.java).build()
        repository = RepositoryImpl(db)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList() {
        val tmpData = TemplateData.getTemplateCurrencies()
        repository.saveCache(tmpData)
        val cache = repository.getFromDb()
        assertEquals(3, cache.size)
//        assertThat(cache.get(0), equalTo(tmpData[0]))
    }



    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.currencyconvertor", appContext.packageName)
    }
}
