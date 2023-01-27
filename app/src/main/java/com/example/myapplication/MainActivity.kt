package com.example.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(),View.OnClickListener {
    private lateinit var one: Button
    private lateinit var two: Button
    private lateinit var three: Button
    private lateinit var four: Button
    private lateinit var five: Button
    private lateinit var six: Button
    private lateinit var seven: Button
    private lateinit var eight: Button
    private lateinit var nine: Button
    private lateinit var zero: Button

    private lateinit var plus: Button
    private lateinit var minus: Button

    private lateinit var clear: Button
    private lateinit var point: Button


    private lateinit var operand: TextView

    private var ispoint = true
    private var isdigit = false

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        one = findViewById(R.id.one)
        two = findViewById(R.id.two)
        three = findViewById(R.id.three)
        four = findViewById(R.id.four)
        five = findViewById(R.id.five)
        six = findViewById(R.id.six)
        seven = findViewById(R.id.seven)
        eight = findViewById(R.id.eight)
        nine = findViewById(R.id.nine)
        zero = findViewById(R.id.zero)

        plus = findViewById(R.id.plus)
        minus = findViewById(R.id.minus)

        clear = findViewById(R.id.clear)
        point = findViewById(R.id.point)

        operand = findViewById(R.id.operand)

        one.setOnClickListener(this)
        two.setOnClickListener(this)
        three.setOnClickListener(this)
        four.setOnClickListener(this)
        five.setOnClickListener(this)
        six.setOnClickListener(this)
        seven.setOnClickListener(this)
        eight.setOnClickListener(this)
        nine.setOnClickListener(this)
        zero.setOnClickListener(this)


    }

    @SuppressLint("SetTextI18n")
    override fun onClick(view: View?) {

    }

    @SuppressLint("SetTextI18n")
    fun addSimvol(simvol: String) {
        if (isdigit) {
            operand.text = operand.text.toString() + simvol
            isdigit = false

        } else {
            if (operand.text != "0") {
                operand.text = operand.text.dropLast(1)
                    .toString() + simvol
            }
        }
        ispoint = true
    }

    fun arr(s: String): MutableList<Any> {
        val list = mutableListOf<Any>()
        var temp = ""
        for (i in s) {
            if (i.isDigit() || i.equals(".")) {
                temp += i
            }
            else{
                list.add(temp)
                list.add(i)
                temp = ""
            }
        }
        if (temp.isNotEmpty()){
            list.add(temp)
        }
        Log.d("list", list.toString())

        return list
    }
}