package com.latypov.currencyconvertor.interface_layout.repository

import com.latypov.currencyconvertor.business_layout.Currency
import com.latypov.currencyconvertor.framework_layout.network.IHttpRequest


interface IRepository {
    suspend fun getCurrencies(req: IHttpRequest):List<Currency>

    fun saveCache(list: List<Currency>)
}