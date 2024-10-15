package com.example.test01

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.test01.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // View binding
    private lateinit var binding: ActivityMainBinding
    private var lastNumeric: Boolean = false
    private var lastDot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Number button listeners
        binding.button0.setOnClickListener { onDigit("0") }
        binding.button1.setOnClickListener { onDigit("1") }
        binding.button2.setOnClickListener { onDigit("2") }
        binding.button3.setOnClickListener { onDigit("3") }
        binding.button4.setOnClickListener { onDigit("4") }
        binding.button5.setOnClickListener { onDigit("5") }
        binding.button6.setOnClickListener { onDigit("6") }
        binding.button7.setOnClickListener { onDigit("7") }
        binding.button8.setOnClickListener { onDigit("8") }
        binding.button9.setOnClickListener { onDigit("9") }

        // Operator button listeners
        binding.buttonAdd.setOnClickListener { onOperator("+") }
        binding.buttonSubtract.setOnClickListener { onOperator("-") }
        binding.buttonMultiply.setOnClickListener { onOperator("*") }
        binding.buttonDivide.setOnClickListener { onOperator("/") }

        // Special button listeners
        binding.buttonClear.setOnClickListener { onClear() }
        binding.buttonEquals.setOnClickListener { onEqual() }
        binding.buttonDot.setOnClickListener { onDecimalPoint() }
        binding.buttonBackspace.setOnClickListener { onBackspace() }
        binding.buttonReset.setOnClickListener { onReset() }
    }

    // Function to handle digit input
    private fun onDigit(digit: String) {
        if (binding.tvInput.text.toString() == "0") {
            binding.tvInput.text = digit
        } else {
            binding.tvInput.append(digit)
        }
        lastNumeric = true
    }

    // Function to handle operator input
    private fun onOperator(operator: String) {
        if (lastNumeric && !isOperatorAdded(binding.tvInput.text.toString())) {
            binding.tvInput.append(operator)
            lastNumeric = false
            lastDot = false
        }
    }

    // Function to handle decimal point input
    private fun onDecimalPoint() {
        if (lastNumeric && !lastDot) {
            binding.tvInput.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    // Function to clear the display
    private fun onClear() {
        binding.tvInput.text = "0"
        lastNumeric = false
        lastDot = false
    }

    // Function to remove the last entered digit/operator
    private fun onBackspace() {
        val currentText = binding.tvInput.text.toString()
        if (currentText.isNotEmpty() && currentText != "0") {
            binding.tvInput.text = currentText.dropLast(1)
        }
        if (binding.tvInput.text.isEmpty()) {
            binding.tvInput.text = "0"
        }
    }

    // Function to calculate the result
    private fun onEqual() {
        if (lastNumeric) {
            var value = binding.tvInput.text.toString()
            var prefix = ""

            try {
                if (value.startsWith("-")) {
                    prefix = "-"
                    value = value.substring(1)
                }

                when {
                    value.contains("-") -> {
                        val splitValue = value.split("-")
                        var one = splitValue[0]
                        val two = splitValue[1]

                        if (prefix.isNotEmpty()) {
                            one = prefix + one
                        }

                        binding.tvInput.text = (one.toDouble() - two.toDouble()).toString()
                    }
                    value.contains("+") -> {
                        val splitValue = value.split("+")
                        binding.tvInput.text = (splitValue[0].toDouble() + splitValue[1].toDouble()).toString()
                    }
                    value.contains("*") -> {
                        val splitValue = value.split("*")
                        binding.tvInput.text = (splitValue[0].toDouble() * splitValue[1].toDouble()).toString()
                    }
                    value.contains("/") -> {
                        val splitValue = value.split("/")
                        if (splitValue[1].toDouble() != 0.0) {
                            binding.tvInput.text = (splitValue[0].toDouble() / splitValue[1].toDouble()).toString()
                        } else {
                            binding.tvInput.text = "Error"
                        }
                    }
                }
            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }
    }

    // Function to reset the calculator to its initial state
    private fun onReset() {
        binding.tvInput.text = "0"
        lastNumeric = false
        lastDot = false
    }

    // Helper function to check if an operator is already added
    private fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/") || value.contains("*") || value.contains("+") || value.contains("-")
        }
    }
}
