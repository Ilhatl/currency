package com.latypov.currencyconvertor.framework_layout.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.latypov.currencyconvertor.business_layout.Currency

@Entity
data class CurrencyEntity(
    @Embedded
    val currency: Currency
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}