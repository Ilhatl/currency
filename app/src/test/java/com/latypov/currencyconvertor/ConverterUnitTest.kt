package com.latypov.currencyconvertor

import com.latypov.currencyconvertor.business_layout.Currency
import org.junit.Test

import org.junit.Assert.*


class ConverterUnitTest {
    var currency: Currency
    init {
        currency = Currency(
            "840",
            "USD",
            1f,
            "Доллар США",
            70.0f
        )
    }


    @Test
    fun convert_to_rub() {
        assertEquals(7000f, currency.toRub(100f))
        assertNotEquals(0f, currency.toRub(100f))
        val tmp = Currency()
        assertEquals(0f, tmp.toRub(100f))
    }

    @Test
    fun convert_to_curr() {
        assertEquals(100f, currency.toCurs(7000f))
        assertNotEquals(0f, currency.toCurs(100f))
        val tmp = Currency()
        assertEquals(0f, tmp.toCurs(100f))
    }


    @Test
    fun curr_to_curr() {
        val target = Currency(
            "978",
            "EUR",
            1f,
            "Евро",
            80.0f
        )
        assertEquals(87.5f, currency.currToCurr(100f,target),0.005f)
        assertNotEquals(0f, currency.currToCurr(100f,target))
        val tmp = Currency()
        assertEquals(0f, tmp.currToCurr(100f,target))
    }
}
