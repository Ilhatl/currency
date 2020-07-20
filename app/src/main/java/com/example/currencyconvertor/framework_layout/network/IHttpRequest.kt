package com.example.currencyconvertor.framework_layout.network

interface IHttpRequest {
    suspend fun httpGetRequest(url:String):String
}