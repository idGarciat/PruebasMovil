package com.example.pruebas1.CRUD

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.room.Database
import androidx.room.Room
import com.example.pruebas1.R
import com.example.pruebas1.bdroom.BaseDatosBDRoom
import com.example.pruebas1.bdroom.LugarBDRoom
import com.example.pruebas1.databinding.ActivityCrudBinding
import com.example.pruebas1.databinding.ActivityNewBdroomBinding
import kotlinx.coroutines.launch
import kotlin.toString

class CRUDActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCrudBinding
    private lateinit var room: DBPlayers
    private lateinit var player: Player
    private var Users: MutableList<Player> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityCrudBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setContentView(R.layout.activity_crud)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        room = Room.databaseBuilder(this, DBPlayers::class.java, "Players").build()


        binding.btnSalir.setOnClickListener {
            finish()
        }

        binding.btnInsertar.setOnClickListener {
//            player = Player(0,
//                binding.EditNombre.text.toString(),
//                binding.EditUser.text.toString(),
//                binding.EditVic.text.toString().toInt(),
//                binding.EditDer.text.toString().toInt()
//            )
//            InsertPlayer(room, player)

            binding.EditNombre.setBackgroundResource(android.R.drawable.edit_text)
            binding.EditUser.setBackgroundResource(android.R.drawable.edit_text)
            binding.EditVic.setBackgroundResource(android.R.drawable.edit_text)
            binding.EditDer.setBackgroundResource(android.R.drawable.edit_text)

            val nombre = binding.EditNombre.text.toString()
            val username = binding.EditUser.text.toString()
            val victorias = binding.EditVic.text.toString()
            val derrotas = binding.EditDer.text.toString()

            if (!sololetras(nombre)) {
                binding.EditNombre.setBackgroundResource(R.drawable.errortext)
                Toast.makeText(this, "Nombre debe contener letras", Toast.LENGTH_SHORT).show()
            } else if (nombre.isEmpty()) {
                binding.EditNombre.setBackgroundResource(R.drawable.errortext)
                Toast.makeText(this, "El campo Nombre está vacío", Toast.LENGTH_SHORT).show()
            } else if (nombre.length < 3 || nombre.length > 30) {
                binding.EditNombre.setBackgroundResource(R.drawable.errortext)
                Toast.makeText(this, "Nombre debe tener entre 3 y 30 caracteres", Toast.LENGTH_SHORT).show()
            } else if (username.isEmpty()) {
                binding.EditUser.setBackgroundResource(R.drawable.errortext)
                Toast.makeText(this, "El campo Username está vacío", Toast.LENGTH_SHORT).show()
            } else if (username.length < 3 || username.length > 30) {
                binding.EditUser.setBackgroundResource(R.drawable.errortext)
                Toast.makeText(this, "Username debe tener entre 3 y 30 caracteres", Toast.LENGTH_SHORT).show()
            } else if (!solonumeros(victorias)) {
                binding.EditVic.setBackgroundResource(R.drawable.errortext)
                Toast.makeText(this, "Victorias solo debe contener números", Toast.LENGTH_SHORT).show()
            } else if (victorias.isEmpty()) {
                binding.EditVic.setBackgroundResource(R.drawable.errortext)
                Toast.makeText(this, "El campo Victorias está vacío", Toast.LENGTH_SHORT).show()
            } else if (!solonumeros(derrotas)) {
                binding.EditDer.setBackgroundResource(R.drawable.errortext)
                Toast.makeText(this, "Derrotas solo debe contener números", Toast.LENGTH_SHORT).show()
            } else if (derrotas.isEmpty()) {
                binding.EditDer.setBackgroundResource(R.drawable.errortext)
                Toast.makeText(this, "El campo Derrotas está vacío", Toast.LENGTH_SHORT).show()
            } else {
                lifecycleScope.launch {
                    val existingPlayer = room.daoPlayer().GetPlayerByUsername(username)
                    if (existingPlayer != null) {
                        binding.EditUser.setBackgroundResource(R.drawable.errortext)
                        Toast.makeText(this@CRUDActivity, "El nombre de usuario ya existe.", Toast.LENGTH_SHORT).show()
                    } else {
                        val player = Player(
                            0,
                            nombre,
                            username,
                            victorias.toInt(),
                            derrotas.toInt()
                        )
                        InsertPlayer(room, player)
                    }
                }
            }

        }
        binding.ListaUsers.setOnClickListener {
            ListarUsuarios(room)
        }
        binding.btnActualizar.setOnClickListener{
//            val player = Player(
//                binding.editID.text.toString().toInt(),
//                binding.EditNombre.text.toString(),
//                binding.EditUser.text.toString(),
//                binding.EditVic.text.toString().toInt(),
//                binding.EditDer.text.toString().toInt()
//            )
//            UpdatePlayer(room, player)
//
            val nombre = binding.EditNombre.text.toString()
            val username = binding.EditUser.text.toString()
            val victorias = binding.EditVic.text.toString()
            val derrotas = binding.EditDer.text.toString()

            if (!sololetras(nombre) || !sololetras(username)) {
                Toast.makeText(this, "Nombre y Username solo deben contener letras", Toast.LENGTH_SHORT).show()
            } else if (!solonumeros(victorias) || !solonumeros(derrotas)) {
                Toast.makeText(this, "Victorias y Derrotas solo deben contener números", Toast.LENGTH_SHORT).show()
            }
            // Second: check if fields are filled
            else if (nombre.isEmpty() || username.isEmpty() || victorias.isEmpty() || derrotas.isEmpty()) {
                Toast.makeText(this, "Llene todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                lifecycleScope.launch {
                    val existingPlayer = room.daoPlayer().GetPlayerByUsername(username)
                    if (existingPlayer != null) {
                        Toast.makeText(this@CRUDActivity, "El nombre de usuario ya existe.", Toast.LENGTH_SHORT).show()
                    } else {
                        val player = Player(
                            0,
                            nombre,
                            username,
                            victorias.toInt(),
                            derrotas.toInt()
                        )
                        InsertPlayer(room, player)
                    }
                }
            }
        }

        binding.VerUser.setOnClickListener {
            //GetPlayer(room, binding.editID.text.toString().toInt())

            val idText = binding.editID.text.toString()
            if (idText.isEmpty()) {
                Toast.makeText(this, "Introduzca un ID", Toast.LENGTH_SHORT).show()
            } else if (!solonumeros(idText)) {
                Toast.makeText(this, "ID solo debe contener números", Toast.LENGTH_SHORT).show()
            } else {
                lifecycleScope.launch {
                    val player = room.daoPlayer().GetUser(idText.toInt())
                    if (player == null) {
                        Toast.makeText(this@CRUDActivity, "Ese ID no existe.", Toast.LENGTH_SHORT).show()
                    } else {
                        GetPlayer(room, idText.toInt())
                    }
                }
            }
        }

        binding.btnBorrar.setOnClickListener{
            //DeletePlayer(room, binding.editID.text.toString().toInt())

            val idText = binding.editID.text.toString()
            if (idText.isEmpty()) {
                Toast.makeText(this, "Introduzca un ID", Toast.LENGTH_SHORT).show()
            } else {
                lifecycleScope.launch {
                    val player = room.daoPlayer().GetUser(idText.toInt())
                    if (player == null) {
                        Toast.makeText(this@CRUDActivity, "Esa ID no existe pa", Toast.LENGTH_SHORT).show()
                    } else {
                        DeletePlayer(room, idText.toInt())
                    }
                }
            }
        }


    }


    private fun InsertPlayer(room: DBPlayers, player: Player) {
        lifecycleScope.launch {
            room.daoPlayer().InsertPlayer(player)
            binding.textViewBD.text = ListarUsuarios(room).toString()
        }
    }

    private fun ListarUsuarios(room: DBPlayers) {
        lifecycleScope.launch {
            Users = room.daoPlayer().GetPlayers()
            binding.textViewBD.text = Users.toString()
        }
    }

    private fun UpdatePlayer(room: DBPlayers, player: Player) {
        lifecycleScope.launch {
            room.daoPlayer().UpdatePlayer(player.id, player.nombre, player.username, player.victorias, player.derrotas)
            ListarUsuarios(room)
        }
    }

    private fun GetPlayer(room: DBPlayers, id: Int) {
        lifecycleScope.launch {
            player = room.daoPlayer().GetUser(id)
            binding.textViewBD.text = player.toString()
            binding.EditNombre.setText(player.nombre)
            binding.EditUser.setText(player.username)
            binding.EditVic.setText(player.victorias.toString())
            binding.EditDer.setText(player.derrotas.toString())

            binding.editID.setText(player.id.toString())

        }
    }

    private fun DeletePlayer(room: DBPlayers, id: Int) {
        lifecycleScope.launch {
            room.daoPlayer().DeletePlayer(id)
            ListarUsuarios(room)
        }
    }

    private fun sololetras(input: String): Boolean =
        input.matches(Regex("^[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ\\s]+$"))

    private fun solonumeros(input: String): Boolean =
        input.matches(Regex("^\\d+$"))
}