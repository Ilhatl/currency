package com.example.currencyconvertor.framework_layout.network

import android.util.Log
import com.example.currencyconvertor.interface_layout.xml_convertor.XMLCurrency
import com.example.currencyconvertor.business_layout.Currency
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URI
import java.net.URL
import java.util.concurrent.TimeoutException


class HttpApiRequest {

    @Throws(RuntimeException::class, TimeoutException::class)
    suspend fun requestGET(url: String?): String? {

        val result = withContext(Dispatchers.IO) {
            val obj = URL(url)
            val con = obj.openConnection() as HttpURLConnection
            con.requestMethod = "GET"
            con.connectTimeout = 1000
            val responseCode = con.responseCode
            Log.d("Response","Response Code :: $responseCode")
            if (responseCode == HttpURLConnection.HTTP_OK) {
                val `in` = BufferedReader(InputStreamReader(con.inputStream))
                var inputLine: String?
                val response = StringBuffer()
                while (`in`.readLine().also { inputLine = it } != null) {
                    response.append(inputLine)
                }
                `in`.close()
                response.toString()
            } else {
                throw RuntimeException("Bad response")
            }
        }

        return result

    }


    @Throws(IOException::class)
    suspend fun http_get(url: String?): String? {
        val response = withContext(Dispatchers.IO) {
            URL(url)
                .openStream()
                .bufferedReader()
                .use { it.readText() }
        }

        return response
    }







}