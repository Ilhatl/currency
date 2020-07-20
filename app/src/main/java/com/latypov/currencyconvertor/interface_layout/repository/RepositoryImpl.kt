package com.latypov.currencyconvertor.interface_layout.repository

import com.latypov.currencyconvertor.business_layout.Currency
import com.latypov.currencyconvertor.framework_layout.db.AppDb
import com.latypov.currencyconvertor.framework_layout.db.CurrencyEntity
import com.latypov.currencyconvertor.framework_layout.network.IHttpRequest
import com.latypov.currencyconvertor.interface_layout.xml_convertor.XMLConverter
import kotlinx.coroutines.*
import java.io.IOException
import java.lang.Exception
import java.util.concurrent.TimeoutException

class RepositoryImpl(db: AppDb): IRepository {
    private var db: AppDb = db
    private val URL = "http://www.cbr.ru/scripts/XML_daily.asp"

    @Throws(RuntimeException::class, TimeoutException::class, IOException::class)
    override suspend fun getCurrencies(req:IHttpRequest): List<Currency> {
        try{
            withTimeoutOrNull(2000L) {
                val data = req.httpGetRequest(URL)
                val list = XMLConverter().convertXML(data)
                list?.let { saveCache(it) }
            }
        }catch (e:Exception){
            e.printStackTrace()
        }

        return getFromDb()

    }

    override fun saveCache(list: List<Currency>) {
        val dao = db.currencyDao()
        dao.clearDb()
        dao.insert(convertFromCurrency(list))
    }

    fun getFromDb():List<Currency>{
        val dao = db.currencyDao()
        return convertToCurrency(dao.getAll())
    }

    private fun convertToCurrency(list:List<CurrencyEntity>):List<Currency>{
        var result = ArrayList<Currency>()
        list.forEach{
            result.add(it.currency)
        }
        return result
    }

    private fun convertFromCurrency(list:List<Currency>):ArrayList<CurrencyEntity>{
        var result = ArrayList<CurrencyEntity>()
        list.forEach{
            val tmp = CurrencyEntity(it)
            result.add(tmp)
        }
        return result
    }

}