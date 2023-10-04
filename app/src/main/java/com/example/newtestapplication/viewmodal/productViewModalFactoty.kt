package com.example.newtestapplication.viewmodal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newtestapplication.Repository.productRepository

class ViewModelFactory(private val repository: productRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(productViewModal::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return productViewModal(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}
