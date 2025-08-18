package com.example.pruebas1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RecursividadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recursividad)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnFactorial: Button = findViewById(R.id.btnFactorial)
        val btnSuma: Button = findViewById(R.id.btnSuma)
        val btnMulti: Button = findViewById(R.id.btnMulti)

        val Num1: EditText = findViewById(R.id.Num1)
        val Num2: EditText = findViewById(R.id.Num2)
        val Resultado: TextView = findViewById(R.id.Resul)


        btnFactorial.setOnClickListener {

            val num1 = Num1.text.toString().toInt()
            val num2 = Num2.text.toString().toInt()

            val recursividad = Recursividad()
            val resultado = recursividad.Potencia(num1, num2)
            Resultado.text = "Resultado: $resultado"
        }

        btnSuma.setOnClickListener {

            val num1 = Num1.text.toString().toInt()
            val num2 = Num2.text.toString().toInt()

            val recursividad = Recursividad()
            val resultado = recursividad.SumaRecursiva(num1, num2)
            Resultado.text = "Resultado: $resultado"
        }

        btnMulti.setOnClickListener {

            val num1 = Num1.text.toString().toInt()
            val num2 = Num2.text.toString().toInt()

            val recursividad = Recursividad()
            val resultado = recursividad.MultiRecursiva(num1, num2)
            Resultado.text = "Resultado: $resultado"

        }




    }
}