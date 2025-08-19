package com.example.pruebas1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isInvisible

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
        val btnPotencia: Button = findViewById(R.id.btnPotencia)
        val btnProcedimiento: Button = findViewById(R.id.btnVerProc)

        val Num1: EditText = findViewById(R.id.Num1)
        val Num2: EditText = findViewById(R.id.Num2)
        val Resultado: TextView = findViewById(R.id.Resul)


        val ResultadoProcedimiento: TextView = findViewById(R.id.TxtVProc)
        val pasos = mutableListOf<String>()



        btnFactorial.setOnClickListener {

            val num1 = Num1.text.toString().toInt()

            val recursividad = Recursividad()
            val resultado = recursividad.factorial(num1)
            Resultado.text = "Resultado: $resultado"

            val resultadoProc = recursividad.factorialConProceso(num1, pasos)
            val textoFinal = buildString {
                append("Cálculo de $num1!\n\n")
                pasos.forEach { append(it).append("\n") }
                append("\nResultado final: $resultado")
            }
            ResultadoProcedimiento.text = textoFinal

        }

        btnSuma.setOnClickListener {

            val num1 = Num1.text.toString().toInt()
            val num2 = Num2.text.toString().toInt()

            val recursividad = Recursividad()
            val resultado = recursividad.sumaRecursiva(num1, num2)
            Resultado.text = "Resultado: $resultado"

        }

        btnMulti.setOnClickListener {

            val num1 = Num1.text.toString().toInt()
            val num2 = Num2.text.toString().toInt()

            val recursividad = Recursividad()
            val resultado = recursividad.multiRecursiva(num1, num2)
            Resultado.text = "Resultado: $resultado"

        }

        btnPotencia.setOnClickListener {

            val num1 = Num1.text.toString().toInt()
            val num2 = Num2.text.toString().toInt()

            val recursividad = Recursividad()
            val resultado = recursividad.potencia(num1, num2)
            Resultado.text = "Resultado: $resultado"



        }

        btnProcedimiento.setOnClickListener{

            if(ResultadoProcedimiento.isInvisible)
            ResultadoProcedimiento.visibility = TextView.VISIBLE
            else ResultadoProcedimiento.visibility = TextView.INVISIBLE

        }
    }
}