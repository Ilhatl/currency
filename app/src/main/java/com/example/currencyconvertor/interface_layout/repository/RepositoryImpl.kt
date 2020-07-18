package com.example.currencyconvertor.interface_layout.repository

import android.content.Context
import com.example.currencyconvertor.business_layout.Currency
import com.example.currencyconvertor.framework_layout.db.AppDb
import com.example.currencyconvertor.framework_layout.db.CurrencyEntity
import com.example.currencyconvertor.interface_layout.repository.IRepository

class RepositoryImpl(db: AppDb): IRepository {
    private var db: AppDb = db

    override fun getCurrencies(): List<Currency> {
        TODO("Not yet implemented")
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

    fun convertToCurrency(list:List<CurrencyEntity>):List<Currency>{
        var result = ArrayList<Currency>()
        list.forEach{
            result.add(it.currency)
        }
        return result
    }

    fun convertFromCurrency(list:List<Currency>):ArrayList<CurrencyEntity>{
        var result = ArrayList<CurrencyEntity>()
        list.forEach{
            val tmp = CurrencyEntity(it)
            result.add(tmp)
        }
        return result
    }

}