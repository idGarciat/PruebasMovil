package com.example.pruebas1.ServiciosWeb

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pruebas1.R
import com.example.pruebas1.databinding.ActivityWebMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WebMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityWebMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setContentView(R.layout.activity_web_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnRecuperar.setOnClickListener {
            getData()
        }
    }

    private fun getData(){
        val apiService = REST.getRestEngine().create(APIServices::class.java)
        val result: Call<List<Album>> = apiService.listAlbums()

        result.enqueue(object : Callback<List<Album>> {
            override fun onResponse(
                call: Call<List<Album>?>,
                response: Response<List<Album>?>
            ) {
                if (response.isSuccessful) {
                    Log.d("Succes", response.body().toString())
                } else {
                    Log.d("Eorr", "Error de respuesta de recurso")
                }
            }

            override fun onFailure(
                call: Call<List<Album>?>,
                t: Throwable
            ) {
                Log.d("Error", "Error de respuesta de servidor")
            }
        })


    }
}