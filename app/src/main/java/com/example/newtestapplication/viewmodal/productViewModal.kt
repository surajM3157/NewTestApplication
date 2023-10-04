package com.example.newtestapplication.viewmodal

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.newtestapplication.Modal.product_detaile
import com.example.newtestapplication.Repository.productRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class productViewModal(private val productRepository: productRepository) : ViewModel() {
    init {
        GlobalScope.launch {
            productRepository.getM()
        }
    }

    val product: LiveData<product_detaile>
        get() = productRepository.product

}