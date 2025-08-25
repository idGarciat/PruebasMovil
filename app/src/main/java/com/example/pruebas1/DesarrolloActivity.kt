package com.example.pruebas1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DesarrolloActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_desarrollo)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val Num1 : EditText = findViewById(R.id.Num1)
        val Num2 : EditText = findViewById(R.id.Num2)

        val btnSuma : Button = findViewById(R.id.buttonSuma)
        val btnResta : Button = findViewById(R.id.buttonResta)
        val btnMulti : Button = findViewById(R.id.buttonMultiplicacion)
        val btnDiv : Button = findViewById(R.id.buttonDivision)

        val Resultado : TextView = findViewById(R.id.txtResultado)
        val Desarrollado : TextView = findViewById(R.id.txtDesarrollo)

        btnSuma.setOnClickListener {

            val operaciones = Operaciones()
            val num1Value = Num1.text.toString().toInt()
            val num2Value = Num2.text.toString().toInt()

            Desarrollado.text = operaciones.buildAdditionString(num1Value, num2Value)
        }


    }
}