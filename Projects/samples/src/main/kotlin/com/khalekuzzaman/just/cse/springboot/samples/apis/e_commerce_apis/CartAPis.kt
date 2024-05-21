package com.khalekuzzaman.just.cse.springboot.samples.apis.e_commerce_apis

import com.khalekuzzaman.just.cse.springboot.samples.apis.e_commerce_apis.data_source.CartDatabase
import com.khalekuzzaman.just.cse.springboot.samples.apis.e_commerce_apis.data_source.ProductDatabase
import com.khalekuzzaman.just.cse.springboot.samples.apis.e_commerce_apis.data_source.UserDatabase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

data class CartItem(
    val quantity: Int,
    val product: Product
)

/**
 * - A cart be unique identified by userId+ProductId
 */
data class CartSchema(
    val userId: String,
    val productId: String,
    val quantity: Int
) {
    val id = userId + productId
}

/**
 * - User [requestId] to confirm the order to be purchase
 */
data class OrderResponse(
    val totalPrice: Int,
    val coupon: String?,
    val discount: Int,
    val requestId: String
)

data class OrderedItem(
    val productId: String,
    val quantity: Int
)

data class OrderRequest(
    val userId: String,
    val coupon: String?,
    val items: List<OrderedItem>,
)


@RestController
@RequestMapping("/api/cart")
class OrderController {
    //Error handling
    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(exception: ClassNotFoundException): ResponseEntity<String> = ResponseEntity(
        exception.message, HttpStatus.NOT_FOUND
    )


    @GetMapping("/get/{userId}")
    fun getCart(@PathVariable userId: String): Collection<CartItem> {
        val items=CartDatabase
            .getCarts(userId)
            .map { cartSchema ->
                val product = ProductDatabase.getProduct(cartSchema.productId)
                if (product != null) {
                    CartItem(
                        quantity = cartSchema.quantity,
                        product = product
                    )
                }
                else null
            }
        println("CartItems:$items")
        return items.mapNotNull { it }

    }


    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)//response code for success
    fun addCart(@RequestBody schema: CartSchema) {
        //automatically JSON will be deserialized to Bank instance
        CartDatabase.addCart(schema)
        //println(CartDatabase.getAllCarts())

    }

    @PostMapping("/order/request")
    @ResponseStatus(HttpStatus.CREATED)//response code for success
    fun orderRequest(@RequestBody request: OrderRequest): OrderResponse {
        println("OrderRequest:Request:$request")
        val userId = request.userId
        val response = ProductDatabase.getOrderResponse(request.items)
        val coupon = request.coupon
        val previousCoupon = UserDatabase.getCoupon(userId)
        println("OrderRequest:PreviousCoupon:$previousCoupon")
        //update now the new coupon code
        UserDatabase.updateCoupon(userId, response.coupon)


        if (!coupon.isNullOrBlank() && previousCoupon == coupon) {
            return response.copy(discount = 200)
        }
        return response

    }

    @ResponseStatus(HttpStatus.CREATED) // Response code for success
    @PatchMapping("/update")
    fun updateCart(@RequestBody carts: List<CartSchema>) {
        CartDatabase.updateCarts(carts)
        println("UpdateCart:${CartDatabase.getAllCarts()}")
    }

    /**
     * - id=userId+ProductId ,if the product id=123, and  userId=admin@g.com then use
     *  "baserUrl/api/cart/admin@g.com123"
     */

    @DeleteMapping("delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCart(@PathVariable id: String) {
        CartDatabase.removeAllCart(id)
        println("CartDeleted:${CartDatabase.getAllCarts()}")
    }
}


