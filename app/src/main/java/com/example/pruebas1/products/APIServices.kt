package com.example.pruebas1.products

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

import io.reactivex.rxjava3.core.Observable

interface APIServices {
    @GET("products")
    fun getAllProducts(): Observable<List<Products>>
    //fun getAllProducts() : Call<List<Products>>

    @POST("products")
    fun insertProduct(@Body product: ProductDTO) : Call<Products>

    @PATCH("products/{id}")
    fun updateProduct(@Path("id") id : Int, @Body product: ProductDTO) : Call<Products>

    @DELETE("products/{id}")
    fun deleteProduct(@Path("id") id : Int) : Call<Int>
}
