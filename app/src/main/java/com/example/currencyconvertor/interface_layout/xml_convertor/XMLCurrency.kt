package com.example.currencyconvertor.interface_layout.xml_convertor

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "ValCurs",strict = false)
data class XMLCurrency(
    @field:ElementList(name = "Valute", inline = true)
    var entriesList: List<Valute>? = null

)

@Root(name = "Valute", strict = false)
data class Valute (
    @field:Element(name = "NumCode")
    var NumCode: String? = null,

    @field:Element(name = "CharCode")
    var CharCode: String? = null,

    @field:Element(name = "Nominal")
    var Nominal: Float? = null,

    @field:Element(name = "Name")
    var Name: String? = null,

    @field:Element(name = "Value")
    var Value: String? = null

)