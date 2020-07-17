package com.example.currencyconvertor

import org.junit.Test

import org.junit.Assert.*


class XMLConverterUnitTest {
    var xml:String
    init {
        xml = "<ValCurs Date=\"16.07.2020\" name=\"Foreign Currency Market\">\n" +
                "<Valute ID=\"R01235\">\n" +
                "<NumCode>840</NumCode>\n" +
                "<CharCode>USD</CharCode>\n" +
                "<Nominal>1</Nominal>\n" +
                "<Name>Доллар США</Name>\n" +
                "<Value>70,7998</Value>\n" +
                "</Valute>\n" +
                "<Valute ID=\"R01239\">\n" +
                "<NumCode>978</NumCode>\n" +
                "<CharCode>EUR</CharCode>\n" +
                "<Nominal>1</Nominal>\n" +
                "<Name>Евро</Name>\n" +
                "<Value>80,8392</Value>\n" +
                "</Valute>\n" +
                "<Valute ID=\"R01820\">\n" +
                "<NumCode>392</NumCode>\n" +
                "<CharCode>JPY</CharCode>\n" +
                "<Nominal>100</Nominal>\n" +
                "<Name>Японских иен</Name>\n" +
                "<Value>66,1835</Value>\n" +
                "</Valute>\n" +
                "</ValCurs>"
    }


    @Test
    fun xml_converter() {
        val converter = XMLConverter()
        val list = converter.convertXML(xml)
        assertEquals(3,list?.size)
        assertEquals(70.7998f,list?.get(0)?.Value)
        assertEquals(66.1835f,list?.get(2)?.Value)
        assertEquals("978",list?.get(1)?.NumCode)

    }


}
