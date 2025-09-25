package com.example.pruebas1.ServiciosWeb

import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

class REST {
    companion object {
        val BASE_URL = "https://jsonplaceholder.typicode.com/"

        fun getRestEngine(): Retrofit {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = okhttp3.OkHttpClient.Builder().addInterceptor(interceptor).build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                .build()

            return retrofit
        }
    }
}