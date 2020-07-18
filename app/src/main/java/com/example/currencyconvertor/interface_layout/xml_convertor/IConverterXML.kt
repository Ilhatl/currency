package com.example.currencyconvertor.interface_layout.xml_convertor

import com.example.currencyconvertor.business_layout.Currency

interface IConverterXML {
    fun convertXML(xml:String):List<Currency>?
}