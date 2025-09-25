package com.example.pruebas1.products

import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

class REST {
    companion object {
        val BASE_URL = "https://10.250.21.15910.250.21.159/products/"

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