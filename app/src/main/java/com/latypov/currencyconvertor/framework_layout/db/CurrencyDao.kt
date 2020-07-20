package com.latypov.currencyconvertor.framework_layout.db

import androidx.room.*

@Dao
interface CurrencyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insert(entity: ArrayList<CurrencyEntity>)

    @Query("SELECT * FROM CurrencyEntity")
    fun getAll(): List<CurrencyEntity>

    @Query("DELETE FROM CurrencyEntity")
    fun clearDb()

}