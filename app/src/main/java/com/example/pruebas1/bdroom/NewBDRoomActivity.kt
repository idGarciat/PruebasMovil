package com.example.pruebas1.bdroom

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.pruebas1.R
import com.example.pruebas1.databinding.ActivityNewBdroomBinding
import kotlinx.coroutines.launch

class NewBDRoomActivity : AppCompatActivity() {


    private lateinit var binding: ActivityNewBdroomBinding
    private lateinit var room: BaseDatosBDRoom
    private lateinit var lugar: LugarBDRoom
    private var listaLugares: MutableList<LugarBDRoom> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //setContentView(R.layout.activity_main)
        // Inicializar el binding
        binding = ActivityNewBdroomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        room = Room.databaseBuilder(this, BaseDatosBDRoom::class.java, "dbPruebas2").build()


        binding.btnSalir.setOnClickListener {
            finish()
        }

        binding.btnInsertar.setOnClickListener {
            lugar = LugarBDRoom(0,
                binding.EditNombre.text.toString(),
                binding.editDescripcion.text.toString(),
                binding.editLatitud.text.toString().toDouble(),
                binding.editLongitud.text.toString().toDouble()
            )
            agregarLugar(room, lugar)
        }
        binding.Verlista.setOnClickListener {
            listarLugares(room)
        }
        binding.btnActualizar.setOnClickListener{
            val lugar = LugarBDRoom(
                binding.editID.text.toString().toInt(),
                binding.EditNombre.text.toString(),
                binding.editDescripcion.text.toString(),
                binding.editLatitud.text.toString().toDouble(),
                binding.editLongitud.text.toString().toDouble()
            )
            actualizarLugar(room, lugar)
        }
        binding.btnBorrar.setOnClickListener{
            borrarLugar(room, binding.editID.text.toString().toInt())
        }

    } //Fin de oncreate

    private fun agregarLugar(room: BaseDatosBDRoom, lugar: LugarBDRoom) {
        lifecycleScope.launch {
            room.daoLugar().insertarLugar(lugar)
            binding.textViewBD.text = listarLugares(room).toString()
        }
    }

    private fun listarLugares(room: BaseDatosBDRoom) {
        lifecycleScope.launch {
            listaLugares = room.daoLugar().obtenerLugares()
            binding.textViewBD.text = listaLugares.toString()
        }
    }

    private fun actualizarLugar(room: BaseDatosBDRoom, lugar: LugarBDRoom) {
        lifecycleScope.launch {
            room.daoLugar().actualizarLugar(lugar.id, lugar.nombre, lugar.descripcion, lugar.latitud, lugar.longitud)
            listarLugares(room)
        }
    }

    private fun borrarLugar(room: BaseDatosBDRoom, id: Int) {
        lifecycleScope.launch {
            room.daoLugar().eliminarLugar(id)
            listarLugares(room)
        }
    }

}