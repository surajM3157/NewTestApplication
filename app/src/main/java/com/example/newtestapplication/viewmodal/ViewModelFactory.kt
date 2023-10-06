package com.example.newtestapplication.viewmodal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newtestapplication.Repository.productRepository
import com.example.newtestapplication.Roomdatabase.ContactDAO

class ViewModelFactory(private val repository: productRepository,private val contactDAO: ContactDAO) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(productViewModal::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return productViewModal(repository,contactDAO) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}
