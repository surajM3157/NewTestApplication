package com.example.newtestapplication


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newtestapplication.Adapter.ProductAdapter
import com.example.newtestapplication.Modal.Meal
import com.example.newtestapplication.Repository.productRepository
import com.example.newtestapplication.Roomdatabase.ContactDAO
import com.example.newtestapplication.Roomdatabase.ContactDatabase
import com.example.newtestapplication.api.ApiInterface
import com.example.newtestapplication.api.ApiUtility
import com.example.newtestapplication.databinding.ActivityDetaileBinding
import com.example.newtestapplication.viewmodal.ViewModelFactory
import com.example.newtestapplication.viewmodal.productViewModal

class DetaileActivity : AppCompatActivity() {

    private lateinit var viewModal: productViewModal
    private lateinit var binding: ActivityDetaileBinding
    private lateinit var contactDao: ContactDAO
    private lateinit var database: ContactDatabase

    val productList = mutableListOf<Meal>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetaileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        database = ContactDatabase.getInstance(applicationContext)
        contactDao = database.contactDao()

        val apiInterface = ApiUtility.getInstance().create(ApiInterface::class.java)
        val productRepository = productRepository(apiInterface)
        val viewModelFactory = ViewModelFactory(productRepository,contactDao)
        viewModal = ViewModelProvider(this, viewModelFactory).get(productViewModal::class.java)

        val adapter = ProductAdapter(this, productList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModal.product.observe(this) { productDetail ->
            productList.clear()
            productList.addAll(productDetail.meals)
            adapter.notifyDataSetChanged()
        }

        adapter.setOnItemClickListener(object : ProductAdapter.OnItemClickListener {
            override fun onItemClick(meal: Meal) {
                 viewModal.insertData(meal)

                val message = "Item clicked: ${meal.strMeal}"
                Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()

            }
        })

    }
}