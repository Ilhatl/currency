package com.example.currencyconvertor

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CurrencyEntity(
    @PrimaryKey val id: Int
):Currency()