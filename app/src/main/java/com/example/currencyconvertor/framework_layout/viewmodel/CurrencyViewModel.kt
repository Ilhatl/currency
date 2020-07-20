package com.example.currencyconvertor.framework_layout.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.currencyconvertor.business_layout.Currency
import com.example.currencyconvertor.framework_layout.db.AppDb
import com.example.currencyconvertor.framework_layout.network.HttpApiRequest
import com.example.currencyconvertor.interface_layout.repository.RepositoryImpl
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class CurrencyViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDb.getDatabase(application)
    private val repository = RepositoryImpl(db)
    private var mFromCurrency:Currency? = null
    private var mToCurrency:Currency? = null
    val currencyListLD: MutableLiveData<ArrayList<Currency>> = MutableLiveData()
    val progress: MutableLiveData<Boolean> = MutableLiveData()
    val convert: MutableLiveData<Boolean> = MutableLiveData()

    init{
        getCurrencies()
    }

    private fun getCurrencies(){
        val req = HttpApiRequest()
        runBlocking {
            val job = async{
                progress.postValue(true)
                repository.getCurrencies(req)
            }
            val list = job.await() as ArrayList<Currency>
            list.add(rouble())
            currencyListLD.value = list
            progress.postValue(false)
        }
    }

    private fun rouble():Currency{
        return Currency("1","RUB",1f,"Российский рубль",1f)
    }

    fun setFrom(currency:Currency){
        this.mFromCurrency = currency
    }

    fun setTo(currency:Currency){
        this.mToCurrency = currency
    }


    fun convert(qnt:Float):Float{
//        if(mFromCurrency?.CharCode.equals("RUB")){
//            return mToCurrency?.toRub(qnt) ?: 0f
//        }
//        if(mToCurrency?.CharCode.equals("RUB")){
//            return mFromCurrency?.toRub(qnt) ?: 0f
//        }

        return mToCurrency?.let { mFromCurrency?.currToCurr(qnt, it) } ?: 0f
    }

}

class CurrencyViewModelFactory(val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CurrencyViewModel(application) as T
    }
}