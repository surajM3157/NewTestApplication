package com.example.newtestapplication.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newtestapplication.Modal.product_detaile
import com.example.newtestapplication.api.ApiInterface

class productRepository(private val apiInterface: ApiInterface) {
    private val productLiveData = MutableLiveData<product_detaile>()
    val product: LiveData<product_detaile>
        get() = productLiveData

   suspend fun getM() {
        val result = apiInterface.getContect()
        if (result.body() != null) {
            productLiveData.postValue(result.body())
        }
    }
}