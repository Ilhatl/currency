package com.example.currencyconvertor

import android.accounts.Account


interface IRepository {
    fun getCurrencies():List<Currency>

    fun saveCache(list: List<Currency>)
}