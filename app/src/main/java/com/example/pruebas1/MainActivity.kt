package com.example.pruebas1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pruebas1.CRUD.CRUDActivity
import com.example.pruebas1.bdroom.NewBDRoomActivity
import com.example.pruebas1.products.ProductsActivity


class MainActivity : AppCompatActivity() {





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }




        val boton1 : Button = findViewById(R.id.boton1)
        val buttonmaps : Button = findViewById(R.id.maps)
        val buttonbasedatos : Button = findViewById(R.id.btnBasededatos)
        val buttongraficos : Button = findViewById(R.id.grafico)
        val buttonsalir : Button = findViewById(R.id.btnSalir)
        val btnBDRoom : Button = findViewById(R.id.btnBDRoom)
        val brnCRUD : Button = findViewById(R.id.btnCRUD)


        boton1.setOnClickListener {
            val intent = Intent(this, OperacionesActivity::class.java)
            startActivity(intent)
        }

        buttonmaps.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }
        buttonbasedatos.setOnClickListener {
            val intent = Intent(this, BDViewActivity::class.java)
            startActivity(intent)
        }
        buttongraficos.setOnClickListener {
            val intent = Intent(this, GraficoActivity::class.java)
            startActivity(intent)
        }

        btnBDRoom.setOnClickListener {
            val intent = Intent(this, NewBDRoomActivity::class.java)
            startActivity(intent)
        }

        brnCRUD.setOnClickListener {
            val intent = Intent(this, ProductsActivity::class.java)
            startActivity(intent)
        }

        buttonsalir.setOnClickListener {
            finish()
        }



    }
}