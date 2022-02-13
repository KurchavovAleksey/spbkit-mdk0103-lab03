package com.spbkit.kurchavov204.lab04

import android.app.AlertDialog
import android.content.Context
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast


class CommoditiesUI() {
    private val commoditiesFromUI = mutableListOf<CommodityUI>()
    var totalSumTextView: TextView? = null
    var currentNotifier: Notifier? = null

    fun addUICommodity(checkBoxIncluded: CheckBox, textViewCount: EditText, textViewPrice: EditText, textViewTotalPrice: TextView) {
        commoditiesFromUI.add(
            CommodityUI(checkBoxIncluded, textViewCount, textViewPrice, textViewTotalPrice)
        )
    }

    fun calculate() {
        var sum = 0f
        try {
            for (commodity in commoditiesFromUI) {
                val commoditiesPrice = commodity.getTotalPrice()
                sum += commoditiesPrice
                commodity.setCommodityTotal()
            }
            setTotalSum(sum.toString())

        } catch (e: NoValueException) {
            setTotalSum(e.message ?: "Error")
        }
    }

    private fun setTotalSum(msg: String) {
        totalSumTextView?.text = msg
        currentNotifier?.notify(msg)
    }
}

data class CommodityUI(
    val checkBoxIncluded: CheckBox,
    val textViewCount: EditText,
    val textViewPrice: EditText,
    val textViewTotalPrice: TextView
    ) {

    private val name = checkBoxIncluded.text.toString()
    private val count: Int
        get() = run {
            return try {
                textViewCount.text.toString().toInt()
            } catch (e: NumberFormatException) {
                throw NoValueException("No value in number $name field")
            }
        }

    private val price: Float
        get() = run {
            return try {
                textViewPrice.text.toString().toFloat()
            } catch (e: NumberFormatException) {
                throw NoValueException("No value in price $name field")
            }
        }

    fun getTotalPrice(): Float {
        return if (checkBoxIncluded.isChecked) {
            count * price
        } else {
            0f
        }
    }

    fun setCommodityTotal() {
        textViewTotalPrice.text = getTotalPrice().toString()
    }
}

class NoValueException(message: String) : Exception(message)


abstract class Notifier {
    abstract fun notify(msg: String): Unit
}

class ToastNotifier(private val ctx: Context) : Notifier() {

    override fun notify(msg: String) {
        Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show()
    }
}

class WindowBoxNotifier(private val ctx: Context): Notifier() {
    override fun notify(msg: String) {
        val builder = AlertDialog.Builder(ctx)
        builder.create()
        builder.setIcon(R.drawable.dialog_img)
        builder.setTitle(ctx.getString(R.string.total_commodity))
        builder.setMessage(msg)
        builder.show()
    }
}