package com.example.currencyconvertor.framework_layout.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.currencyconvertor.R
import com.example.currencyconvertor.business_layout.Currency
import com.example.currencyconvertor.framework_layout.db.AppDb
import com.example.currencyconvertor.framework_layout.spinner_adapters.CurrencySpinnerAdapter
import com.example.currencyconvertor.framework_layout.viewmodel.CurrencyViewModel
import com.example.currencyconvertor.framework_layout.viewmodel.CurrencyViewModelFactory
import com.example.currencyconvertor.interface_layout.repository.RepositoryImpl
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.lang.Exception
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var fromAdapter: CurrencySpinnerAdapter
    private lateinit var toAdapter: CurrencySpinnerAdapter
    private var currencyList: ArrayList<Currency> = ArrayList()
    private lateinit var viewModel: CurrencyViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
        initAdapters()
        observeLiveData()
        btnConvert()
    }

    private fun observeLiveData(){
        viewModel.currencyListLD.observe(this, Observer {
            currencyList.clear()
            currencyList.addAll(it)
            fromAdapter.notifyDataSetChanged()
            toAdapter.notifyDataSetChanged()
        })
    }

    private fun initViewModel(){
        viewModel = ViewModelProviders.of(this, CurrencyViewModelFactory(application)).get(CurrencyViewModel::class.java)
    }

    private fun initAdapters(){
        fromAdapter = CurrencySpinnerAdapter(this,android.R.layout.simple_spinner_item,currencyList)
        toAdapter = CurrencySpinnerAdapter(this,android.R.layout.simple_spinner_item,currencyList)
        spFrom.adapter = fromAdapter
        spFrom.onItemSelectedListener = selectListener
        spTo.adapter = toAdapter
        spTo.onItemSelectedListener = selectListener
    }

    private fun btnConvert(){
        btnConvert.setOnClickListener{
            val qnt_raw = etQnt.text.toString()
            var qnt = 0f
            try{
                qnt = qnt_raw.toFloat()
            }catch (e:Exception){
                e.printStackTrace()
            }
            tvResult.text = viewModel.convert(qnt).toString()
        }
    }

    private val selectListener: AdapterView.OnItemSelectedListener = object: AdapterView.OnItemSelectedListener {
        override fun onItemSelected(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ) {
            val c = parent?.getItemAtPosition(position) as Currency
            if(parent.id == R.id.spFrom){
                viewModel.setFrom(c)
            }
            if(parent.id == R.id.spTo){
                viewModel.setTo(c)
            }

        }

        override fun onNothingSelected(p0: AdapterView<*>?) {

        }
    }



}
