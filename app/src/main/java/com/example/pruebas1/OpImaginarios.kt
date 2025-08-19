package com.example.pruebas1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.toString

class OpImaginarios : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_op_imaginarios)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val complejos = Complejos()

        val editTextA: EditText = findViewById(R.id.editTextA)
        val editTextB: EditText = findViewById(R.id.editTextB)
        val editTextC: EditText = findViewById(R.id.editTextC)
        val editTextD: EditText = findViewById(R.id.editTextD)



        val buttonSumar: Button = findViewById(R.id.buttonSumar)
        val buttonMulti: Button = findViewById(R.id.buttonMultiplicar)
        val buttonDiv: Button = findViewById(R.id.buttonDividir)

        val Resultado: TextView  = findViewById<TextView>(R.id.Resultado)



        buttonSumar.setOnClickListener {
            val a = editTextA.text.toString().toInt()
            val b = editTextB.text.toString().toInt()
            val c = editTextC.text.toString().toInt()
            val d = editTextD.text.toString().toInt()

            //val pReal = a + c
            //val pImaginario = b + d
            //Resultado.text = "Resultado: $pReal + ${pImaginario}i"

            Resultado.text = complejos.suma(a, b, c, d)
        }

        buttonMulti.setOnClickListener {
            val a = editTextA.text.toString().toInt()
            val b = editTextB.text.toString().toInt()
            val c = editTextC.text.toString().toInt()
            val d = editTextD.text.toString().toInt()


            Resultado.text = complejos.multiplicacion(a, b, c, d)
        }

        buttonDiv.setOnClickListener {
            val a = editTextA.text.toString().toInt()
            val b = editTextB.text.toString().toInt()
            val c = editTextC.text.toString().toInt()
            val d = editTextD.text.toString().toInt()

            Resultado.text = complejos.division(a, b, c, d)

        }




    }
}