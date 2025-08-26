package com.example.pruebas1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pruebas1.databinding.ActivityBdviewBinding

class BDViewActivity : AppCompatActivity() {


    private lateinit var binding: ActivityBdviewBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        binding = ActivityBdviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setContentView(R.layout.activity_bdview)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val dbHelper = DBHelper(this)

        binding.btnInsertar.setOnClickListener{

            val nombre = binding.EditNombre.text.toString()
            val descripcion = binding.editDescripcion.text.toString()
            val latitud = binding.editLatitud.text.toString().toDoubleOrNull() ?: 0.0
            val longitud = binding.editLongitud.text.toString().toDoubleOrNull() ?: 0.0

            val lugar = Lugar(nombre = nombre, descripcion = descripcion, latitud = latitud, longitud = longitud)
            val id = dbHelper.insertarLugar(lugar)

            if (id != -1L) {
                binding.textID.text = "ID: $id"
            } else {
                binding.textID.text = "Error al insertar el lugar"
            }

        }

        binding.Verlista.setOnClickListener {

//            dbHelper.mostrarLugares()
            val lugares = dbHelper.mostrarLugares()
            val builder = StringBuilder()
            for (lugar in lugares) {
                builder.append("ID: ${lugar.id}\n")
                builder.append("Nombre: ${lugar.nombre}\n")
                builder.append("Descripción: ${lugar.descripcion}\n")
                builder.append("Latitud: ${lugar.latitud}\n")
                builder.append("Longitud: ${lugar.longitud}\n")
                builder.append("\n")
            }
            binding.textViewBD.text = builder.toString()

        }

        binding.btnActualizar.setOnClickListener{

            val id = binding.editID.text.toString().toIntOrNull() ?: 0
            val nombre = binding.EditNombre.text.toString()
            val descripcion = binding.editDescripcion.text.toString()
            val latitud = binding.editLatitud.text.toString().toDoubleOrNull() ?: 0.0
            val longitud = binding.editLongitud.text.toString().toDoubleOrNull() ?: 0.0

            val lugar = Lugar(id = id, nombre = nombre, descripcion = descripcion, latitud = latitud, longitud = longitud)
            val filasAfectadas = dbHelper.actualizarLugar(lugar)

            if (filasAfectadas > 0) {
                binding.textID.text = "Lugar actualizado correctamente"
            } else {
                binding.textID.text = "Error al actualizar el lugar"
            }


        }

        binding.btnBorrar.setOnClickListener{
            val id = binding.editID.text.toString().toIntOrNull() ?: 0
            val filasAfectadas = dbHelper.borrarLugar(id)

            if (filasAfectadas > 0) {
                binding.textID.text = "Lugar actualizado correctamente"
            } else {
                binding.textID.text = "Error al actualizar el lugar"
            }


        }

        binding.btnSalir.setOnClickListener{
            finish()


        }


    }
}