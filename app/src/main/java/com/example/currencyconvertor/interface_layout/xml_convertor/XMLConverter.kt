package com.example.currencyconvertor.interface_layout.xml_convertor

import com.example.currencyconvertor.business_layout.Currency
import org.simpleframework.xml.core.Persister

class XMLConverter:IConverterXML {
    override fun convertXML(xml:String): List<Currency>? {
        if(xml.isEmpty()) return null
        val xmlData = convertToListCurrency(xml)
        val list = ArrayList<Currency>()

        if(xmlData.entriesList!=null){
            for(item in xmlData.entriesList!!){
                list.add(elemConvert(item))
            }
        }
        return list
    }

    private fun convertToListCurrency(xml:String): XMLCurrency {
        val serializer = Persister()
        return serializer.read(XMLCurrency::class.java, xml)
    }

    private fun elemConvert(xml: Valute): Currency {
        val tmp = xml.Value?.replace(",",".")
        val f = tmp?.toFloat()

        return Currency(
            xml.NumCode!!,
            xml.CharCode!!,
            xml.Nominal!!,
            xml.Name!!,
            f!!
        )
    }
}