package com.example.pruebas1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class OperacionesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_operaciones)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val buttonComplejosActivity: Button = findViewById(R.id.buttonOpImaginarios)
        val buttonRecursividadActivity: Button = findViewById(R.id.buttonRecursividad)
        val buttonsDesOp: Button = findViewById(R.id.buttonDesarrollo)



        buttonComplejosActivity.setOnClickListener {
            val intent = Intent(this, OpImaginariosActivity::class.java)
            startActivity(intent)
        }

        buttonRecursividadActivity.setOnClickListener {
            val intent = Intent(this, RecursividadActivity::class.java)
            startActivity(intent)
        }

        buttonsDesOp.setOnClickListener {
            val intent = Intent(this, DesarrolloActivity::class.java)
            startActivity(intent)
        }





    }
}