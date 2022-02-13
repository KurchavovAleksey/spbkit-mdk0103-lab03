package com.spbkit.kurchavov204.lab04

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioGroup


class MainActivity : AppCompatActivity() {
    private val goods = CommoditiesUI()
    private val toaster = ToastNotifier(this)
    private val dialoger = WindowBoxNotifier(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        goods.totalSumTextView = findViewById(R.id.textViewTotalSum)
        goods.currentNotifier = toaster

        goods.addUICommodity(
            findViewById(R.id.checkBoxApple),
            findViewById(R.id.editTextNumberApple),
            findViewById(R.id.editTextPriceApple),
            findViewById(R.id.textViewTotalApple)
        )

        goods.addUICommodity(
            findViewById(R.id.checkBoxStrawberry),
            findViewById(R.id.editTextNumberStrawberry),
            findViewById(R.id.editTextPriceStrawberry),
            findViewById(R.id.textViewTotalStrawberry)
        )

        goods.addUICommodity(
            findViewById(R.id.checkBoxBlueberry),
            findViewById(R.id.editTextNumberBlueberry),
            findViewById(R.id.editTextPriceBlueberry),
            findViewById(R.id.textViewTotalBlueberry)
        )

        goods.addUICommodity(
            findViewById(R.id.checkBoxPotatoes),
            findViewById(R.id.editTextNumberPotatoes),
            findViewById(R.id.editTextPricePotatoes),
            findViewById(R.id.textViewTotalPotatoes)
        )

        findViewById<Button>(R.id.buttonCalculate).setOnClickListener(View.OnClickListener {
            goods.calculate()
        })

        findViewById<RadioGroup>(R.id.radioGroupNotification).setOnCheckedChangeListener { radioGroup, buttonID ->
            when(buttonID) {
                R.id.radioButtonToast -> goods.currentNotifier = toaster
                R.id.radioButtonDialogWindow -> goods.currentNotifier = dialoger
            }
        }
    }


}