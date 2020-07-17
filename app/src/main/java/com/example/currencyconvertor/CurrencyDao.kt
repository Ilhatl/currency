package com.example.currencyconvertor

import androidx.room.*

@Dao
interface CurrencyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(setting: CurrencyEntity)

    @Update
    fun update(setting: CurrencyEntity)

    @Delete
    fun delete(setting: CurrencyEntity)

    @Query("SELECT * FROM CurrencyEntity")
    fun getAll(): List<CurrencyEntity>

}