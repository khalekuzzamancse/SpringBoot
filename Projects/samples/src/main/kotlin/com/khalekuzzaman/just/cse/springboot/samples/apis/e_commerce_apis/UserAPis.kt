package com.khalekuzzaman.just.cse.springboot.samples.apis.e_commerce_apis

import com.khalekuzzaman.just.cse.springboot.samples.apis.e_commerce_apis.data_source.UserDatabase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * @param email used as id
 */
data class UserSchema(
    val name: String,
    val email: String,
    val password: String,
    val coupon: String? = null,
)

@RestController
@RequestMapping("/api/user")
class UserController {
    //Error handling
    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(exception: ClassNotFoundException): ResponseEntity<String> = ResponseEntity(
        exception.message, HttpStatus.NOT_FOUND
    )


    @GetMapping("/get/{userId}")
    fun getUser(@PathVariable userId: String): UserSchema? {
        return UserDatabase
            .getUser(userId)
    }

    @GetMapping("/get-coupon/{userId}")
    fun getCoupon(@PathVariable userId: String): String? {
        return UserDatabase
            .getCoupon(userId)
    }


    /**
     * - XXX-Unsed_XXX
     * - UserId=email
     */
    @ResponseStatus(HttpStatus.CREATED) // Response code for success
    @PatchMapping("/update-coupon/{userId}")
    fun updateCart(@PathVariable userId: String, @RequestBody coupon: String?) {
        UserDatabase.updateCoupon(userId, coupon)
        println("UpdateCoupon:${UserDatabase.allUsers()}")
    }

}
