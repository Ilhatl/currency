package com.example.currencyconvertor

import com.example.currencyconvertor.interface_layout.utility.TestData
import com.example.currencyconvertor.interface_layout.xml_convertor.XMLConverter
import org.junit.Test

import org.junit.Assert.*


class XMLConverterUnitTest {
    var xml:String
    init {
        xml = TestData.getTemplateXML()
    }


    @Test
    fun xml_converter() {
        val converter =
            XMLConverter()
        val list = converter.convertXML(xml)
        assertEquals(3,list?.size)
        assertEquals(70.7998f,list?.get(0)?.Value)
        assertEquals(66.1835f,list?.get(2)?.Value)
        assertEquals("978",list?.get(1)?.NumCode)

    }


}
