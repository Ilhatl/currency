package com.latypov.currencyconvertor.interface_layout.xml_convertor

import com.latypov.currencyconvertor.business_layout.Currency

interface IConverterXML {
    fun convertXML(xml:String):List<Currency>?
}