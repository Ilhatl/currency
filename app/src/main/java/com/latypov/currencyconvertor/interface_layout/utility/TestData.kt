package com.latypov.currencyconvertor.interface_layout.utility

import com.latypov.currencyconvertor.business_layout.Currency
import kotlinx.coroutines.delay

class TestData {
    companion object{
        fun getTemplateXML():String{
            return  "<ValCurs Date=\"16.07.2020\" name=\"Foreign Currency Market\">\n" +
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

        fun getTemplateCurrencies():ArrayList<Currency>{
            val list = ArrayList<Currency>()
            list.add(Currency("840","USD",1f,"Доллар США",70.7998f))
            list.add(Currency("978","EUR",1f,"Евро",80.8392f))
            list.add(Currency("392","JPY",1f,"Японских иен",66.1835f))
            return list
        }

        fun getCachedTemplateCurrencies():ArrayList<Currency>{
            val list = ArrayList<Currency>()
            list.add(Currency("840","USD",1f,"Доллар США",71.7998f))
            list.add(Currency("978","EUR",1f,"Евро",81.8392f))
            list.add(Currency("392","JPY",1f,"Японских иен",67.1835f))
            return list
        }

        suspend fun success_get_request():String{
            delay(500)
            return getTemplateXML()
        }

        suspend fun empty_response():String{
            delay(500)
            return ""
        }

        suspend fun timeout_request():String{
            delay(2500)
            return ""
        }
    }
}