package com.example.newtestapplication.api

import com.example.newtestapplication.Modal.product_detaile
import com.google.android.gms.analytics.ecommerce.Product
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("filter.php?c=Seafood")
     suspend fun getContect() : Response<product_detaile>
}