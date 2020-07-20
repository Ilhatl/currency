package com.example.currencyconvertor.interface_layout.repository

import com.example.currencyconvertor.business_layout.Currency
import com.example.currencyconvertor.framework_layout.network.IHttpRequest


interface IRepository {
    suspend fun getCurrencies(req: IHttpRequest):List<Currency>

    fun saveCache(list: List<Currency>)
}