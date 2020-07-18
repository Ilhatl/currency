package com.example.currencyconvertor.interface_layout.repository

import com.example.currencyconvertor.business_layout.Currency


interface IRepository {
    fun getCurrencies():List<Currency>

    fun saveCache(list: List<Currency>)
}