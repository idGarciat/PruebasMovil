package com.example.pruebas1.CRUD

import android.os.Bundle
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
            player = Player(0,
                binding.EditNombre.text.toString(),
                binding.EditUser.text.toString(),
                binding.EditVic.text.toString().toInt(),
                binding.EditDer.text.toString().toInt()
            )
            InsertPlayer(room, player)
        }
        binding.ListaUsers.setOnClickListener {
            ListarUsuarios(room)
        }
        binding.btnActualizar.setOnClickListener{
            val player = Player(
                binding.editID.text.toString().toInt(),
                binding.EditNombre.text.toString(),
                binding.EditUser.text.toString(),
                binding.EditVic.text.toString().toInt(),
                binding.EditDer.text.toString().toInt()
            )
            UpdatePlayer(room, player)
        }

        binding.VerUser.setOnClickListener {
            GetPlayer(room, binding.editID.text.toString().toInt())

        }

        binding.btnBorrar.setOnClickListener{
            DeletePlayer(room, binding.editID.text.toString().toInt())
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
}