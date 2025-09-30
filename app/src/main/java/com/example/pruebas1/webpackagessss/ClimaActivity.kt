package com.example.pruebas1.webpackagessss

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pruebas1.R
import com.example.pruebas1.databinding.ActivityClimaBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class ClimaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityClimaBinding
    private lateinit var weatherClient: WeatherWebSocketClient
    private var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityClimaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setContentView(R.layout.activity_clima)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        weatherClient = WeatherWebSocketClient()

        disposable = weatherClient.connect("ws://192.168.0.181:8080")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { message ->
                    // Extract weather type from message
                    val weatherType = message.substringAfter(": ").trim()
                    val emoji = getWeatherEmoji(weatherType)
                    binding.textViewResult.text = "$emoji $weatherType"
                },
                { error -> binding.textViewResult.text = "❌ Error: ${error.message}" }
            )

    }

    private fun getWeatherEmoji(weather: String): String {
        return when (weather) {
            "Soleado" -> "☀️"
            "Nublado" -> "☁️"
            "Lluvioso" -> "🌧️"
            "Tormenta" -> "⛈️"
            else -> "❓"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }

}