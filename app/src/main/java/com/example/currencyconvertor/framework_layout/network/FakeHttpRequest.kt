package com.example.currencyconvertor.framework_layout.network

import com.example.currencyconvertor.interface_layout.utility.TestData
import java.io.IOException

class FakeHttpRequest(type:Int):IHttpRequest {
    val type = type
    companion object{
        val SUCCESS = 1
        val TIMEOUT = 2
        val EMPTY_RESPONSE = 3
    }

    override suspend fun httpGetRequest(url: String): String = when (type){
        SUCCESS-> TestData.success_get_request()
        TIMEOUT-> TestData.timeout_request()
        EMPTY_RESPONSE-> TestData.empty_response()
        else -> TestData.success_get_request()
    }


}