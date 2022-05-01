package com.example.tip_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tip_calculator.databinding.ActivityMainBinding
import java.text.NumberFormat

lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener {
            calculateTip()
        }
    }

    private fun calculateTip() {
        val stringInTextField = binding.cost.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        if (cost == null) {
            return
        }

        val selectedId = binding.radioGroup.checkedRadioButtonId
        val tipPercentage = when (selectedId) {
            R.id.percent20 -> 0.20
            R.id.percent18 -> 0.18
            else -> 0.15
        }
        var tip = tipPercentage * cost
        val roundUp = binding.switchround.isChecked

        if (roundUp) {
            tip = kotlin.math.ceil(tip)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)

    }
}