package com.spbkit.kurchavov204.lab04

import android.app.AlertDialog
import android.content.Context
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast


class CommoditiesUI() {
    private val commoditiesFromUI = mutableListOf<CommodityUI>()  // Курчавов Алексей 204 группа СПБКИТ
    var totalSumTextView: TextView? = null
    var currentNotifier: Notifier? = null  // Курчавов Алексей 204 группа СПБКИТ

    fun addUICommodity(checkBoxIncluded: CheckBox, textViewCount: EditText, textViewPrice: EditText, textViewTotalPrice: TextView) {
        commoditiesFromUI.add(  // Курчавов Алексей 204 группа СПБКИТ
            CommodityUI(checkBoxIncluded, textViewCount, textViewPrice, textViewTotalPrice)
        )
    }  // Курчавов Алексей 204 группа СПБКИТ

    fun calculate() {  // Курчавов Алексей 204 группа СПБКИТ
        var sum = 0f
        try {
            for (commodity in commoditiesFromUI) {
                val commoditiesPrice = commodity.getTotalPrice()
                sum += commoditiesPrice  // Курчавов Алексей 204 группа СПБКИТ
                commodity.setCommodityTotal()
            }
            setTotalSum(sum.toString())

        } catch (e: NoValueException) {
            setTotalSum(e.message ?: "Error")  // Курчавов Алексей 204 группа СПБКИТ
        }
    }

    private fun setTotalSum(msg: String) {
        totalSumTextView?.text = msg
        currentNotifier?.notify(msg)  // Курчавов Алексей 204 группа СПБКИТ
    }
}

data class CommodityUI(
    val checkBoxIncluded: CheckBox,  // Курчавов Алексей 204 группа СПБКИТ
    val textViewCount: EditText,
    val textViewPrice: EditText,
    val textViewTotalPrice: TextView
    ) {

    private val name = checkBoxIncluded.text.toString()
    private val count: Int  // Курчавов Алексей 204 группа СПБКИТ
        get() = run {
            return try {
                textViewCount.text.toString().toInt()
            } catch (e: NumberFormatException) {
                throw NoValueException("No value in number $name field")
            }  // Курчавов Алексей 204 группа СПБКИТ
        }

    private val price: Float
        get() = run {
            return try {
                textViewPrice.text.toString().toFloat()  // Курчавов Алексей 204 группа СПБКИТ
            } catch (e: NumberFormatException) {
                throw NoValueException("No value in price $name field")
            }
        }
  // Курчавов Алексей 204 группа СПБКИТ
    fun getTotalPrice(): Float {
        return if (checkBoxIncluded.isChecked) {
            count * price
        } else {
            0f
        }  // Курчавов Алексей 204 группа СПБКИТ
    }

    fun setCommodityTotal() {
        textViewTotalPrice.text = getTotalPrice().toString()  // Курчавов Алексей 204 группа СПБКИТ
    }
}

class NoValueException(message: String) : Exception(message)  // Курчавов Алексей 204 группа СПБКИТ


abstract class Notifier {
    abstract fun notify(msg: String): Unit  // Курчавов Алексей 204 группа СПБКИТ
}

class ToastNotifier(private val ctx: Context) : Notifier() {

    override fun notify(msg: String) {  // Курчавов Алексей 204 группа СПБКИТ
        Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show()
    }
}

class WindowBoxNotifier(private val ctx: Context): Notifier() {
    override fun notify(msg: String) {
        val builder = AlertDialog.Builder(ctx)  // Курчавов Алексей 204 группа СПБКИТ
        builder.create()
        builder.setIcon(R.drawable.dialog_img)
        builder.setTitle(ctx.getString(R.string.total_commodity))
        builder.setMessage(msg)  // Курчавов Алексей 204 группа СПБКИТ
        builder.show()
    }
}