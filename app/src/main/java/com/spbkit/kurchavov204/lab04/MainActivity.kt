package com.spbkit.kurchavov204.lab04

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioGroup


class MainActivity : AppCompatActivity() {
    private val goods = CommoditiesUI()  // Курчавов Алексей 204 группа СПБКИТ
    private val toaster = ToastNotifier(this)
    private val dialoger = WindowBoxNotifier(this)


    override fun onCreate(savedInstanceState: Bundle?) {  // Курчавов Алексей 204 группа СПБКИТ
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        goods.totalSumTextView = findViewById(R.id.textViewTotalSum)
        goods.currentNotifier = toaster  // Курчавов Алексей 204 группа СПБКИТ

        goods.addUICommodity(
            findViewById(R.id.checkBoxApple),
            findViewById(R.id.editTextNumberApple),
            findViewById(R.id.editTextPriceApple),  // Курчавов Алексей 204 группа СПБКИТ
            findViewById(R.id.textViewTotalApple)
        )

        goods.addUICommodity(
            findViewById(R.id.checkBoxStrawberry),  // Курчавов Алексей 204 группа СПБКИТ
            findViewById(R.id.editTextNumberStrawberry),
            findViewById(R.id.editTextPriceStrawberry),
            findViewById(R.id.textViewTotalStrawberry)
        )
          // Курчавов Алексей 204 группа СПБКИТ
        goods.addUICommodity(
            findViewById(R.id.checkBoxBlueberry),
            findViewById(R.id.editTextNumberBlueberry),
            findViewById(R.id.editTextPriceBlueberry),  // Курчавов Алексей 204 группа СПБКИТ
            findViewById(R.id.textViewTotalBlueberry)
        )

        goods.addUICommodity(
            findViewById(R.id.checkBoxPotatoes),  // Курчавов Алексей 204 группа СПБКИТ
            findViewById(R.id.editTextNumberPotatoes),
            findViewById(R.id.editTextPricePotatoes),
            findViewById(R.id.textViewTotalPotatoes)
        )  // Курчавов Алексей 204 группа СПБКИТ

        findViewById<Button>(R.id.buttonCalculate).setOnClickListener(View.OnClickListener {
            goods.calculate()
        })  // Курчавов Алексей 204 группа СПБКИТ

        findViewById<RadioGroup>(R.id.radioGroupNotification).setOnCheckedChangeListener { radioGroup, buttonID ->
            when(buttonID) {
                R.id.radioButtonToast -> goods.currentNotifier = toaster
                R.id.radioButtonDialogWindow -> goods.currentNotifier = dialoger
            }
        }  // Курчавов Алексей 204 группа СПБКИТ
    }


}  // Курчавов Алексей 204 группа СПБКИТ