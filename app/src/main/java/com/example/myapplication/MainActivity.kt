package com.example.myapplication

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

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
    private lateinit var percent: Button
    private lateinit var equal: Button
    private lateinit var plus: Button
    private lateinit var minus: Button
    private lateinit var div: Button
    private lateinit var multiply: Button
    private lateinit var backspace: ImageButton

    private lateinit var answer: TextView
    private lateinit var operand: TextView
    private var isok = false
    private var symbol = false
    private var number = false
    private var equal_status = false
    private var limit = 0


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
        equal = findViewById(R.id.equal)
        answer = findViewById(R.id.answer)
        backspace = findViewById(R.id.backspace)
        percent = findViewById(R.id.percent)
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

        backspace.setOnClickListener {
            if (operand.text != "0" && !equal_status) {
                operand.text = operand.text.substring(0, operand.text.length - 1)
                var st = calculate(toArray(operand.text.toString()))
                answer.text = "=" + st.substring(1, st.length - 1)
            }
            if (operand.text.isEmpty() && !equal_status) operand.text = "0"
            if (operand.text[operand.text.length - 1] != '+' || operand.text[operand.text.length - 1] != '-' || operand.text[operand.text.length - 1] != '*' || operand.text[operand.text.length - 1] != '÷') {
                symbol = false

            }
        }

        equal.setOnClickListener {
            var str = calculate(toArray(operand.text.toString()))
            answer.text = "=" + str.substring(1, str.length - 1)
            operand.textSize = 20f
            answer.textSize = 30f
            answer.setTextColor(Color.parseColor("#FFFFFF"))
            operand.setTextColor(Color.parseColor("#4E505F"))
            equal_status = true
        }
        point.setOnClickListener {
            if ((!isok && number && symbol) || operand.text.toString() == "0") {
                operand.text = operand.text.toString() + "."
                isok = true
            }
        }
        clear.setOnClickListener {
            operand.text = "0"
            answer.text = "=0"
            isok = false
            symbol = false
            equal_status = false
            operand.textSize = 30f
            answer.textSize = 20f
            answer.setTextColor(Color.parseColor("#4E505F"))
            operand.setTextColor(Color.parseColor("#FFFFFF"))
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
        if (limit <= 15) {
            if (operand.text != ("0")) {
                operand.text = operand.text.toString() + btn.text
            } else operand.text = btn.text
        }
        symbol = true
        number = true
        equal_status = false
        limit++
        var str1 = calculate(toArray(operand.text.toString()))
        answer.text = "=" + str1.substring(1, str1.length - 1)
    }

    @SuppressLint("SetTextI18n")
    fun symbol_onClick(simbol: String) {
        if (symbol) {
            operand.text = operand.text.toString() + simbol
            symbol = false
        } else {
            if (operand.text != ("0")&&(operand.text[operand.text.length - 1] == '+' || operand.text[operand.text.length - 1] == '-' || operand.text[operand.text.length - 1] == '*' || operand.text[operand.text.length - 1] == '÷')) {
                operand.text = operand.text.dropLast(1)
                operand.text = operand.text.toString() + simbol
            } else {
                operand.text = operand.text.toString() + simbol
            }
        }
        number = false
        isok = false
        limit = 0
    }

    fun toArray(str: String): MutableList<Any> {
        var arr: MutableList<Any> = mutableListOf()
        var int1 = 0.0
        var int2: Double
        var isok2 = false
        var int3 = 10.0
        for (i in str.indices) {

            if (Character.isDigit(str[i])) {
                if (!isok2)
                    int1 = int1 * 10 + str[i].toInt() - 48
                else {
                    int2 = ((str[i].toInt() - 48) / int3)
                    int1 += int2
                    int3 *= 10
                }
            }
            if (str[i] == '.') {
                isok2 = true
            }
            if (str[i] == '+' || str[i] == '-' || str[i] == '*' || str[i] == '÷') {
                arr.add(int1)
                arr.add(str[i])
                int1 = 0.0
                isok2 = false
                int2 = 0.0
                int3 = 10.0
            }
        }
        arr.add(int1)
        Log.d("list", arr.toString())
        return arr

    }

    fun calculate(arr: MutableList<Any>): String {
        var res = ""
        var a: Double
        var b: Double
        var i = 0
        var c = arr.size
        while (i < c) {
            when (arr[i]) {
                '*' -> {
                    a = arr[i - 1] as Double
                    b = arr[i + 1] as Double
                    arr[i - 1] = a * b
                    arr.removeAt(i)
                    arr.removeAt(i)
                    i = 0
                    c -= 2
                }
                '÷' -> {
                    a = arr[i - 1] as Double
                    b = arr[i + 1] as Double
                    arr[i - 1] = a / b
                    arr.removeAt(i)
                    arr.removeAt(i)
                    i = 0
                    c -= 2
                }
            }
            i++
        }
        i = 0
        c = arr.size
        while (i < c) {
            when (arr[i]) {
                '+' -> {
                    a = arr[i - 1] as Double
                    b = arr[i + 1] as Double
                    arr[i - 1] = a + b
                    arr.removeAt(i)
                    arr.removeAt(i)
                    i = 0
                    c -= 2
                }
                '-' -> {
                    a = arr[i - 1] as Double
                    b = arr[i + 1] as Double
                    arr[i - 1] = a - b
                    arr.removeAt(i)
                    arr.removeAt(i)
                    i = 0
                    c -= 2
                }
            }
            i++
        }
        res = arr.toString()
        return res
    }


}
