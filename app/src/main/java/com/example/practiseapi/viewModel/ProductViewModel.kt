package com.example.practiseapi.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practiseapi.model.Product
import com.example.practiseapi.network.ApiService
import kotlinx.coroutines.launch
import java.lang.Exception

class ProductViewModel: ViewModel() {
    var productListResponse: List<Product> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")

    fun getProductList(){
        viewModelScope.launch {
            val apiService = ApiService.getInstance()
            try {
                val productList = apiService.getProducts()
                productListResponse = productList
            }catch (e: Exception){
                errorMessage = e.message.toString()
            }
        }
    }
}