package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var minus: Boolean = false
    var newNumber: Boolean = true
    var previousNumber: String = "0"
    var operator: String = "+"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun numberClick(view:View) {
        var btnText: String = editText.text.toString()
        var btnSelect: Button = view as Button

        if (newNumber) {
            btnText = "0"
            minus = false
        }

        newNumber = false

        if (btnText.length < 10 || (btnText.length == 10 && btnSelect.id == plusMinusBTN.id && minus)) {
            when (btnSelect.id) {
                zeroBTN.id -> {
                    if (!btnText.equals("0")) {
                        btnText += "0"
                    }
                }
                oneBTN.id -> {
                    if (btnText.equals("0")) {
                        btnText = "1"
                    } else {
                        btnText += "1"
                    }
                }
                twoBTN.id -> {
                    if (btnText.equals("0")) {
                        btnText = "2"
                    } else {
                        btnText += "2"
                    }
                }
                threeBTN.id -> {
                    if (btnText.equals("0")) {
                        btnText = "3"
                    } else {
                        btnText += "3"
                    }
                }
                fourBTN.id -> {
                    if (btnText.equals("0")) {
                        btnText = "4"
                    } else {
                        btnText += "4"
                    }
                }
                fiveBTN.id -> {
                    if (btnText.equals("0")) {
                        btnText = "5"
                    } else {
                        btnText += "5"
                    }
                }
                sixBTN.id -> {
                    if (btnText.equals("0")) {
                        btnText = "6"
                    } else {
                        btnText += "6"
                    }
                }
                sevenBTN.id -> {
                    if (btnText.equals("0")) {
                        btnText = "7"
                    } else {
                        btnText += "7"
                    }
                }
                eightBTN.id -> {
                    if (btnText.equals("0")) {
                        btnText = "8"
                    } else {
                        btnText += "8"
                    }
                }
                nineBTN.id -> {
                    if (btnText.equals("0")) {
                        btnText = "9"
                    } else {
                        btnText += "9"
                    }
                }
                pointBTN.id -> {
                    if (!btnText.contains('.')) {
                        btnText += "."
                    }
                }
                plusMinusBTN.id -> {
                    if (minus) {
                        btnText = btnText.substring(1, btnText.length)
                        minus = false
                    } else if (btnText != "0") {
                        btnText = "-$btnText"
                        minus = true
                    }
                }
            }
        }

        if (btnText.length > 0) {
            editText.setText(btnText)
        }
    }

    fun clearClick(view:View) {
        editText.setText("0")
        newNumber = true
        previousNumber = "0"
        operator = "+"
    }

    fun operatorClick(view:View) {
        newNumber = true
        var thisNumber: String = editText.text.toString()

        if (thisNumber == "Error") {
            return
        }
        
        var result: Double = 0.0

        when(operator) {
            "/" -> {
                if (thisNumber == "0") {
                    editText.setText("Error")
                    previousNumber = "0"
                    operator = "+"
                    return
                } else {
                    result = previousNumber.toDouble() / thisNumber.toDouble()
                }
            }
            "*" -> result = previousNumber.toDouble() * thisNumber.toDouble()
            "+" -> result = previousNumber.toDouble() + thisNumber.toDouble()
            "-" -> result = previousNumber.toDouble() - thisNumber.toDouble()
        }

        var fractionalPart: String = result.toString().substring(result.toString().indexOf('.') + 1)
        var integerPart: String = result.toString().substring(0, result.toString().indexOf('.'))

        if (fractionalPart.length > 10 - integerPart.length - 1) {
            fractionalPart = fractionalPart.substring(0, 10 - integerPart.length - 1)
        }

        if (fractionalPart.toInt() == 0 && integerPart.length > 10) {
            integerPart = integerPart.substring(0, 10)
        }

        editText.setText(if (fractionalPart.toInt() == 0) integerPart else integerPart + "." + fractionalPart)
        previousNumber = editText.text.toString()
        var btnSelect: Button = view as Button

        when (btnSelect.id) {
            divideBTN.id -> operator = "/"
            multiplyBTN.id -> operator = "*"
            subtractBTN.id -> operator = "-"
            addBTN.id -> operator = "+"
        }
    }

    fun equalClick(view:View) {
        newNumber = true
        var thisNumber: String = editText.text.toString()

        if (thisNumber == "Error") {
            return
        }

        var result: Double = 0.0

        when(operator) {
            "/" -> {
                if (thisNumber == "0") {
                    editText.setText("Error")
                    previousNumber = "0"
                    operator = "+"
                    return
                } else {
                    result = previousNumber.toDouble() / thisNumber.toDouble()
                }
            }
            "*" -> result = previousNumber.toDouble() * thisNumber.toDouble()
            "+" -> result = previousNumber.toDouble() + thisNumber.toDouble()
            "-" -> result = previousNumber.toDouble() - thisNumber.toDouble()
        }

        var fractionalPart: String = result.toString().substring(result.toString().indexOf('.') + 1)
        var integerPart: String = result.toString().substring(0, result.toString().indexOf('.'))

        if (fractionalPart.length > 10 - integerPart.length - 1) {
            fractionalPart = fractionalPart.substring(0, 10 - integerPart.length - 1)
        }

        if (fractionalPart.toInt() == 0 && integerPart.length > 10) {
            integerPart = integerPart.substring(0, 10)
        }

        editText.setText(if (fractionalPart.toInt() == 0) integerPart else integerPart + "." + fractionalPart)
        previousNumber = "0"
        operator = "+"
    }

    fun percentClick(view:View) {
        var percent: Double = editText.text.toString().toDouble() / 100
        editText.setText(percent.toString())
        newNumber = true
    }
}