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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductsBinding


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


        binding.btnMostrarTodos.setOnClickListener {
            getData()
        }

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

    private fun getData(){
        val apiService = REST.getRestEngine().create(APIServices::class.java)
        val result : Call<List<Products>> = apiService.getAllProducts();

        result.enqueue(object : Callback<List<Products>> {
            override fun onResponse(
                call: Call<List<Products>?>,
                response: Response<List<Products>?>
            ) {
                if (response.isSuccessful){
                    Log.d("Success", response.body().toString())
                }else {
                    Log.d("Error", "Error in resource response")
                }
            }

            override fun onFailure(
                call: Call<List<Products>?>,
                t: Throwable
            ) {
                Log.d("Error", "Error in response ${t.message.toString()}")
            }

        });
    }

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