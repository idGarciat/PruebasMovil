package com.example.pruebas1.products

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pruebas1.R
import com.example.pruebas1.databinding.ActivityProductsBinding
import com.example.pruebas1.databinding.ActivityWebMainBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ProductsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductsBinding
    private val disposables = CompositeDisposable()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setContentView(R.layout.activity_products)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Configura Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.250.21.253:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory.create())
            .build()
        // Crea una instancia de ApiService
        val apiService = retrofit.create(APIServices::class.java)
        // Comienza a observar los datos
        observarDatos(apiService)



//        binding.btnMostrarTodos.setOnClickListener {
//            getData()
//        }

        binding.btnagregar.setOnClickListener {
            val name : String = binding.editTextProductName.text.toString();
            val price : Double = binding.editTextProductPrice.text.toString().toDouble();
            val description : String = binding.editTextProductDescription.text.toString();
            val product : ProductDTO = ProductDTO (
                name,
                price,
                description
            );
            this.insertProduct(product)
        }

        binding.btnactualizar.setOnClickListener {
            val id : Int = binding.editTextProductId.text.toString().toInt();
            val name : String = binding.editTextProductName.text.toString();
            val price : Double = binding.editTextProductPrice.text.toString().toDouble();
            val description : String = binding.editTextProductDescription.text.toString();
            val product : ProductDTO = ProductDTO (
                name,
                price,
                description
            );
            this.updateProduct(id,product)
        }

        binding.btnborrar.setOnClickListener {
            val id : Int = binding.editTextProductId.text.toString().toInt();
            this.deleteProduct(id)
        }

    }

    private fun observarDatos(apiService: APIServices) {
        val observable = Observable.interval(5, 5, TimeUnit.SECONDS)
            .flatMap { apiService.getAllProducts() }
            .distinctUntilChanged()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        val disposable = observable.subscribe({ listaDatos ->
            val mensajes = listaDatos.joinToString("\n") { dato ->
                "ID: ${dato.product_id}, Producto: ${dato.name}"
            }
            binding.productListTextView.text = mensajes

            // Show last added product
            if (listaDatos.isNotEmpty()) {
                val lastProduct = listaDatos.last()
                val lastProductText = "Ultimo agregado:\n ID: ${lastProduct.product_id} \n Producto: ${lastProduct.name} \n Precio: ${lastProduct.price} \n"
                binding.lastAddedTextView.text = lastProductText
            } else {
                binding.lastAddedTextView.text = "No products available"
            }
        }, { error ->
            binding.productListTextView.text = "Error: ${error.message}"
            binding.lastAddedTextView.text = "Error: ${error.message}"
        })

        disposables.add(disposable)
    }


    override fun onDestroy() {
        super.onDestroy()
        // Libera los recursos de RxJava cuando la actividad se destruye
        disposables.clear()
    }
//    private fun getData() {
//        val apiService = REST.getRestEngine().create(APIServices::class.java)
//        val result: Observable<List<Products>> = apiService.getAllProducts()
//
//        val disposable = result
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({ productsList ->
//                // Handle the list of products, e.g., display them
//                val mensajes = productsList.joinToString("\n") { dato ->
//                    "ID: ${dato.product_id}, Mensaje: ${dato.name}"
//                }
//                binding.productListTextView.text = mensajes
//            }, { error ->
//                binding.productListTextView.text = "Error: ${error.message}"
//            })
//
//        disposables.add(disposable)
//    }

    private fun insertProduct(product: ProductDTO) {
        val apiService = REST.getRestEngine().create(APIServices::class.java)
        val result : Call<Products> = apiService.insertProduct(product);

        result.enqueue(object : Callback<Products> {
            override fun onResponse(
                call: Call<Products?>,
                response: Response<Products?>
            ) {
                if (response.isSuccessful){
                    Log.d("Success", response.body().toString())
                }else {
                    Log.d("Error", "Error in resource response")
                }
            }

            override fun onFailure(
                call: Call<Products?>,
                t: Throwable
            ) {
                Log.d("Error", "Error in response ${t.message.toString()}")
            }
        });
    }
    private fun updateProduct(id : Int, product : ProductDTO) {
        val apiService = REST.getRestEngine().create(APIServices::class.java)
        val result : Call<Products> = apiService.updateProduct(id, product);

        result.enqueue(object : Callback<Products> {
            override fun onResponse(
                call: Call<Products?>,
                response: Response<Products?>
            ) {
                if (response.isSuccessful){
                    Log.d("Success", response.body().toString())
                }else {
                    Log.d("Error", "Error in resource response")
                }
            }

            override fun onFailure(
                call: Call<Products?>,
                t: Throwable
            ) {
                Log.d("Error", "Error in response ${t.message.toString()}")
            }
        });
    }
    private fun deleteProduct(id : Int) {
        val apiService = REST.getRestEngine().create(APIServices::class.java)
        val result : Call<Int> = apiService.deleteProduct(id);

        result.enqueue(object : Callback<Int> {
            override fun onResponse(
                call: Call<Int?>,
                response: Response<Int?>
            ) {
                if (response.isSuccessful){
                    Log.d("Success", response.body().toString())
                }else {
                    Log.d("Error", "Error in resource response")
                }
            }

            override fun onFailure(
                call: Call<Int?>,
                t: Throwable
            ) {
                Log.d("Error", "Error in response ${t.message.toString()}")
            }
        });
    }
}