package com.khalekuzzaman.just.cse.springboot.samples.apis.e_commerce_apis.data_source

import com.khalekuzzaman.just.cse.springboot.samples.apis.e_commerce_apis.CartSchema
import kotlin.random.Random


object CartDatabase {
    private val carts = mutableListOf<CartSchema>()



    fun addCart(schema: CartSchema) {
        carts.add(schema)
    }

    fun removeAllCart(userId: String) {
        
        carts.removeIf { it.userId == userId }
    }
    fun updateCarts(newCarts: List<CartSchema>) {

        newCarts.forEach { newCart ->
            val existingCartIndex = carts.indexOfFirst { it.id == newCart.id }
            if (existingCartIndex != -1) {
                // Cart exists, update it
                carts[existingCartIndex] = newCart
            } else {
                // Cart does not exist, add new cart
                // Uncomment the next line if you want to add new carts that don't exist
                // carts.add(newCart)
            }
        }
    }
    fun getCarts(userId: String): List<CartSchema>
    = carts.filter { it.userId == userId }
    fun getAllCarts()= carts.toList()

    private fun addDummyCart() {
        //Adding first 4 items to cart
        ProductDatabase.productsList.take(3).forEach { product ->
            addCart(
                CartSchema(
                    userId = "admin",
                    productId = product.id,
                    quantity = Random.nextInt(1, 10)
                )
            )

        }
    }


}