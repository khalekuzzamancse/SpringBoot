package com.khalekuzzaman.just.cse.springboot.samples.apis.e_commerce_apis.data_source

import com.khalekuzzaman.just.cse.springboot.samples.apis.e_commerce_apis.*
import java.util.*
import kotlin.random.Random

object ProductDatabase {


    val productsList = listOf(
        Product(
            id = "1",
            name = "Nokia C32",
            images = listOf("https://d61s2hjse0ytn.cloudfront.net/card_image/None/blackcard.webp"), // Replace with actual image URL
            price = 12999,
            description = "Stylish design with a 6.5-inch HD+ display, dual-camera with 50MP primary lens, powered by Unisoc SC9863A1 CPU.",
            type = "Electronics",
            amountAvailable = 5
        ),
        Product(
            id = "2",
            name = "Nokia 8000",
            images = listOf("https://welectronics.com/images/stories/virtuemart/product/Nokia8000gold6.jpg"),
            price = 5205,
            description = "Features a 2.8-inch TFT LCD display, Qualcomm Snapdragon 210 chipset, 4G support, and a 1500 mAh removable battery.",
            type = "Electronics",
            amountAvailable = 5
        ),
        Product(
            id = "3",
            name = "Nokia G10",
            images = listOf("https://d61s2hjse0ytn.cloudfront.net/card_image/None/106card.webp"), // Replace with actual image URL
            price = 12499,
            description = "6.52-inch IPS LCD display, triple camera setup, powered by Mediatek MT6762G Helio G25, large battery capacity.",
            type = "Electronics",
            amountAvailable = 5
        ),
        Product(
            id = "4",
            name = "Nokia C12 Pro",
            images = listOf("https://mobilebazar.com.bd/assets/img/Apple_iPhone_SE_(2022).webp"), // Replace with actual image URL
            price = 8999,
            description = "Budget-friendly with a 6.3-inch display, Unisoc SC9863A1 chipset, 4000mAh battery, ideal for efficient daily performance.",
            type = "Electronics",
            amountAvailable = 5
        ),
        Product(
            id = "5",
            name = "Nokia G20",
            images = listOf("https://d61s2hjse0ytn.cloudfront.net/card_image/None/Nokia_110_2023_card.webp"), // Replace with actual image URL
            price = 18999,
            description = "Features a 6.52-inch display, quad-camera setup, Mediatek MT6765G Helio G35 chipset, supports NFC and Android updates.",
            type = "Electronics",
            amountAvailable = 5
        ),
        Product(
            id = "6",
            name = "Samsung Galaxy S24 Ultra",
            images = listOf("https://d61s2hjse0ytn.cloudfront.net/card_image/None/nokia_c2_2nd_card.webp"),
            price = 1200,
            description = "Features a 6.8-inch display, Snapdragon 898, 12GB RAM, 108MP main camera, and 5000mAh battery.",
            type = "Electronics",
            amountAvailable = 10
        ),
        Product(
            id = "7",
            name = "Apple iPhone 14 Pro Max",
            images = listOf("https://d61s2hjse0ytn.cloudfront.net/card_image/None/Nokia_150_2023_Cart.webp"),
            price = 1100,
            description = "Features a 6.7-inch Super Retina XDR display, A16 Bionic chip, 6GB RAM, triple camera system, and enhanced battery life.",
            type = "Electronics",
            amountAvailable = 8
        ),
        Product(
            id = "8",
            name = "Google Pixel 8",
            images = listOf("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ9DIujrFio7TaE_ZQv_1K0eNZN1y0lErozyHrhPrwMEA&s"),
            price = 999,
            description = "Features a 6.3-inch display, Google Tensor chip, 8GB RAM, 50MP dual-camera setup, and stock Android experience.",
            type = "Electronics",
            amountAvailable = 15
        ),
        Product(
            id = "9",
            name = "OnePlus 10 Pro",
            images = listOf("https://diamu.com.bd/wp-content/uploads/2020/01/Apple-Iphone-11-White.jpg"),
            price = 899,
            description = "Features a 6.7-inch AMOLED display, Snapdragon 898, 12GB RAM, 50MP triple camera setup, and 5000mAh battery with fast charging.",
            type = "Electronics",
            amountAvailable = 12
        ),
        Product(
            id = "10",
            name = "Nokia G50 5G",
            images = listOf("https://gadgetandphone.com/public/uploads/all/Pu0hx7Cm2IQfBlC1qQ7DLyIXtBVzx2gMbSEEzm15.jpg"),
            price = 350,
            description = "Features a 6.82-inch display, Snapdragon 480, 4GB RAM, 48MP main camera, and supports 5G connectivity.",
            type = "Electronics",
            amountAvailable = 20
        )
    )


    fun getProduct(id: String) = productsList.find { it.id == id }


    fun getOrderResponse(items: List<OrderedItem>):OrderResponse {
        var total = 0
        items.forEach { item ->
            val product = productsList.find { it.id == item.productId }
            if (product != null) {
                val price = product.price * item.quantity
                total += price
            }
        }

        val coupon = if (total >= 5000) Random.nextInt(1000, 9999).toString() else null


        return OrderResponse(
            totalPrice = total,
            coupon = coupon, //new coupon,used for next time
            discount =0 ,//while generating coupon not discount
            requestId = UUID.randomUUID().toString()
        )
    }
}