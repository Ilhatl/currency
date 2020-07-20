package com.latypov.currencyconvertor.framework_layout.spinner_adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.latypov.currencyconvertor.business_layout.Currency
import java.util.ArrayList

class CurrencySpinnerAdapter(
    c: Context,
    textViewResourceId: Int,
    v: ArrayList<Currency>
) :  ArrayAdapter<Currency>(c, textViewResourceId, v) {

    private val ctx: Context = c
    private val values: ArrayList<Currency> = v

    override fun getCount(): Int {
        return values.size
    }

    override fun getItem(position: Int): Currency {
        return values[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("SetTextI18n")
    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {
        val label = TextView(ctx)
        label.setTextColor(Color.BLACK)
        label.setPadding(5, 10, 5, 10)
        label.textSize = 16f
        label.setSingleLine(true)
        label.setText("${values[position].CharCode}-${values[position].Name}")
        return label
    }

    @SuppressLint("SetTextI18n")
    override fun getDropDownView(
        position: Int, convertView: View?,
        parent: ViewGroup
    ): View {
        val label = TextView(ctx)
        label.setTextColor(Color.BLACK)
        label.setPadding(5, 10, 5, 10)
        label.textSize = 16f
        label.setText("${values[position].CharCode}-${values[position].Name}")
        return label
    }

}