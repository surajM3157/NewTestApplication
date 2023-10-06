package com.example.newtestapplication.viewmodal

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.newtestapplication.Modal.Meal
import com.example.newtestapplication.Modal.product_detaile
import com.example.newtestapplication.Repository.productRepository
import com.example.newtestapplication.Roomdatabase.ContactDAO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class productViewModal(
    private val productRepository: productRepository,
    private val contactDao: ContactDAO
) : ViewModel() {
    fun insertData(meal: Meal) {
        GlobalScope.launch {
            contactDao.insertContact(meal)
        }
    }

    init {
        GlobalScope.launch {
            productRepository.getM()
        }
    }

    val product: LiveData<product_detaile>
        get() = productRepository.product

}