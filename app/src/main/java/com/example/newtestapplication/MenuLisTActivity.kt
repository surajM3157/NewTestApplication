package com.example.newtestapplication

import MenuAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newtestapplication.Modal.Meal
import com.example.newtestapplication.Repository.productRepository
import com.example.newtestapplication.Roomdatabase.ContactDAO
import com.example.newtestapplication.Roomdatabase.ContactDatabase
import com.example.newtestapplication.api.ApiInterface
import com.example.newtestapplication.api.ApiUtility
import com.example.newtestapplication.databinding.ActivityMenuLisTactivityBinding
import com.example.newtestapplication.viewmodal.ViewModelFactory
import com.example.newtestapplication.viewmodal.productViewModal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MenuLisTActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMenuLisTactivityBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModal: productViewModal
    private lateinit var adapter: MenuAdapter
    private lateinit var contactDao:ContactDAO
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuLisTactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = ContactDatabase.getInstance(applicationContext)
        contactDao = database.contactDao()
        val apiInterface = ApiUtility.getInstance().create(ApiInterface::class.java)
        val repository = productRepository(apiInterface)
        recyclerView = findViewById(R.id.recyclerView) // Replace with your RecyclerView's ID

        // Initialize ViewModel
        val viewModelFactory = ViewModelFactory(repository, contactDao)
        viewModal = ViewModelProvider(this, viewModelFactory).get(productViewModal::class.java)


        adapter = MenuAdapter(this,
            object : MenuAdapter.OnItemClickListener {
                override fun onItemClick(meal: Meal) {
                    // Handle item click if needed
                }
            },
            object : MenuAdapter.OnDeleteItemClickListener {
                override fun onDeleteItemClick(meal: Meal) {
                    deleteMealFromDatabase(meal)
                }
            }
        )



        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Observe the LiveData from ViewModel
        viewModal.product_detaile.observe(this) { mealList ->
            // Submit the new data to the adapter
            adapter.submitList(mealList)
        }
    }

    private fun deleteMealFromDatabase(meal: Meal) {
        // Use a CoroutineScope to perform database operations on a background thread
        CoroutineScope(Dispatchers.IO).launch {
            contactDao.deleteContact(meal)
        }
    }
}
