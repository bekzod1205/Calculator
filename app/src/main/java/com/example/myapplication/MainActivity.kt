package com.example.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var zero: Button
    private lateinit var one: Button
    private lateinit var two: Button
    private lateinit var three: Button
    private lateinit var four: Button
    private lateinit var five: Button
    private lateinit var six: Button
    private lateinit var seven: Button
    private lateinit var eight: Button
    private lateinit var nine: Button
    private lateinit var clear: Button
    private lateinit var plus_minus: Button
    private lateinit var point: Button
    private lateinit var foiz: Button
    private lateinit var equal: Button
    private lateinit var plus: Button
    private lateinit var minus: Button
    private lateinit var div: Button
    private lateinit var multiply: Button

    private lateinit var answer: TextView
    private lateinit var operand: TextView
    private var isok = false
    private var symbol = false
    private var number = false

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        zero = findViewById(R.id.zero)
        one = findViewById(R.id.one)
        two = findViewById(R.id.two)
        three = findViewById(R.id.three)
        four = findViewById(R.id.four)
        five = findViewById(R.id.five)
        six = findViewById(R.id.six)
        seven = findViewById(R.id.seven)
        eight = findViewById(R.id.eight)
        nine = findViewById(R.id.nine)
        operand = findViewById(R.id.operand)
        point = findViewById(R.id.point)
        clear = findViewById(R.id.clear)
        plus = findViewById(R.id.plus)
        minus = findViewById(R.id.minus)
        div = findViewById(R.id.div)
        multiply = findViewById(R.id.multiply)
        one.setOnClickListener(this)
        two.setOnClickListener(this)
        three.setOnClickListener(this)
        four.setOnClickListener(this)
        five.setOnClickListener(this)
        six.setOnClickListener(this)
        seven.setOnClickListener(this)
        eight.setOnClickListener(this)
        nine.setOnClickListener(this)


        point.setOnClickListener {
            if (!isok && !symbol) {
                operand.text = operand.text.toString() + "."
                isok = true
            }
        }
        clear.setOnClickListener {
            operand.text = "0"
            isok = false
            symbol = false
        }
        plus.setOnClickListener {
            symbol_onClick(plus.text.toString())
        }
        minus.setOnClickListener {
            symbol_onClick(minus.text.toString())
        }
        div.setOnClickListener {
            symbol_onClick(div.text.toString())
        }
        multiply.setOnClickListener {
            symbol_onClick(multiply.text.toString())
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onClick(view: View) {
        var btn = findViewById<Button>(view.id)
        if (operand.text != ("0")) {
            operand.text = operand.text.toString() + btn.text
        } else operand.text = btn.text
        symbol = true
        number = true

    }

    @SuppressLint("SetTextI18n")
    fun symbol_onClick(simbol: String) {
        if (symbol) {
            operand.text = operand.text.toString() + simbol
            symbol = false
            number = false
            if (number) {
                isok = false
            }
        } else {
            operand.text = operand.text.dropLast(1)
            operand.text = operand.text.toString() + simbol
        }
    }

}