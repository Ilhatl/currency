package com.latypov.currencyconvertor.framework_layout.network

interface IHttpRequest {
    suspend fun httpGetRequest(url:String):String
}