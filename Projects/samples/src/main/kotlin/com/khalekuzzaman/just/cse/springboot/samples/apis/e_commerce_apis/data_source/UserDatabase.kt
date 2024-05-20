package com.khalekuzzaman.just.cse.springboot.samples.apis.e_commerce_apis.data_source

import com.khalekuzzaman.just.cse.springboot.samples.apis.e_commerce_apis.UserSchema

object UserDatabase {
    private val users = mutableListOf<UserSchema>()

    init {
        createDummyUser()
    }


    fun addUser(userSchema: UserSchema) {
        users.add(userSchema)
    }
    fun allUsers()= users.toList()
    fun getUser(email: String)= users.find { it.email==email }
    fun updateCoupon(email:String,coupon:String?){
        users.forEach { user ->
            val index = users.indexOfFirst { email == user.email }
            if (index != -1) {
                // Cart exists, update it
                users[index] = user.copy(coupon=coupon)
            } else {
                // Cart does not exist, add new cart
                // Uncomment the next line if you want to add new carts that don't exist
                // carts.add(newCart)
            }
        }
    }
    fun getCoupon(useId:String)= users.find { it.email==useId }?.coupon


    private fun createDummyUser() {
        val users = listOf(
            UserSchema(
                name = "Admin",
                email = "admin",
                password = "admin"
            )
        )
        users.forEach {
            addUser(it)
        }

    }
}