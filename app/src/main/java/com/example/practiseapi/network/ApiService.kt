package com.example.practiseapi.network

import com.example.practiseapi.model.Product
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("products")
    suspend fun getProducts(): List<Product>

    companion object{
        var apiService: ApiService? = null
        fun getInstance(): ApiService{
            if (apiService == null){
                apiService = Retrofit.Builder()
                    .baseUrl("https://dummyjson.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiService::class.java)
            }
            return apiService!!
        }
    }
}