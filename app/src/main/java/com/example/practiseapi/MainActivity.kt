package com.example.practiseapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practiseapi.model.Product
import com.example.practiseapi.ui.theme.PractiseApiTheme
import com.example.practiseapi.view.ProductItem
import com.example.practiseapi.viewModel.ProductViewModel

class MainActivity : ComponentActivity() {
    val productViewModel by viewModels<ProductViewModel> ()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PractiseApiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background

                ) {
                    if (productViewModel.errorMessage.isNotBlank()) {
                        // Display the error message
                        ErrorScreen(errorMessage = productViewModel.errorMessage)
                    }else {
                        // Display the product list
                        ProductList(productList = productViewModel.productListResponse)
                    }
                }
            }
        }
        productViewModel.getProductList()
    }
}

@Composable
fun ErrorScreen(errorMessage: String) {
    // Display the error message
    Text(
        text = errorMessage,
        style = MaterialTheme.typography.labelMedium,
        modifier = Modifier.fillMaxSize().padding(16.dp)
    )
}

@Composable
fun ProductList(productList: List<Product>){
    LazyColumn{
        itemsIndexed(items = productList){index, item ->
            ProductItem(product = item)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PractiseApiTheme {
           val product = Product("Apple", "smartphones", "An apple", 12.96,
               1, listOf(
                   "https://i.dummyjson.com/data/products/1/1.jpg",
                   "https://i.dummyjson.com/data/products/1/2.jpg"
               ), 549, 4.69,  94,
               "https://i.dummyjson.com/data/products/1/thumbnail.jpg", "iPhone 9"
           )
        ProductItem(product = product)
    }
}