package com.example.currencyconvertor

open class Currency(){

    constructor( NumCode:String,
                 CharCode:String,
                 Nominal:Float,
                 Name:String,
                 Value:Float) : this() {
        this.NumCode = NumCode
        this.CharCode = CharCode
        this.Nominal = Nominal
        this.Name = Name
        this.Value = Value

    }

    var NumCode:String = ""
    var CharCode:String = ""
    var Nominal:Float = 0f
    var Name:String = ""
    var Value:Float = 0f

    fun toRub(qnt:Float):Float{
        var total = 0f
        if(this.Nominal>0 || this.Value>0){
            total = qnt*this.Value/this.Nominal
        }
        return total

    }

    fun toCurs(qnt:Float):Float{
        var total = 0f
        if(this.Nominal>0 || this.Value>0){
            total = qnt*this.Nominal/this.Value
        }
        return total
    }

    fun currToCurr(qnt:Float, target:Currency):Float{
        var total = 0f
        if(this.Nominal>0 || this.Value>0){
            val rub = this.Value/this.Nominal
            val targetRub = target.Value/target.Nominal
            total = qnt*rub/targetRub
        }
        return total
    }

}