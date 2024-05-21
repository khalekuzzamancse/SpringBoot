package com.khalekuzzaman.just.cse.springboot.samples.apis.e_commerce_apis

import com.khalekuzzaman.just.cse.springboot.samples.apis.e_commerce_apis.data_source.ProductDatabase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import kotlin.NoSuchElementException

data class Product(
    val id: String,
    val name: String,
    val images: List<String>,
    val price: Int,
    val description: String,
    val type: String,
    val amountAvailable: Int
)

@RestController
@RequestMapping("/api/product")
class ProductController {
    //Error handling
    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(exception: ClassNotFoundException): ResponseEntity<String> = ResponseEntity(
        exception.message, HttpStatus.NOT_FOUND
    )
    //get all product

    @GetMapping
    fun getProducts(): Collection<Product> {
        return ProductDatabase.productsList

    }
    //get product by id for description
    @GetMapping("/{id}")
    fun getProduct(@PathVariable id: String):Product? {
        return ProductDatabase.productsList.find { it.id==id }

    }

}




