package com.khalekuzzaman.just.cse.springboot.samples.apis

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
data class Bank(
        val account: String,
        val trust: Double,
        val fee: Int
)
@RestController
@RequestMapping("/api/banks")
class BankController {
    //Error handling
    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(exception: ClassNotFoundException): ResponseEntity<String> = ResponseEntity(
        exception.message, HttpStatus.NOT_FOUND
    )

    @GetMapping
    fun getBanks(): Collection<Bank> {
        return listOf(
                Bank("Md Khalekuzzaman",1.0,15),
                Bank("Md Abdul Hossain",10.0,30)
        )

    }

    @GetMapping("/{account}")
    fun getBank(@PathVariable account: String): Bank {
        return Bank("Test Account", 12.0, 40)
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)//response code for success
    fun addBank(@RequestBody bank: Bank){
        //automatically JSON will be deserialized to Bank instance
        println("PostRequest:${bank}")

    }
    @ResponseStatus(HttpStatus.CREATED)//response code for success
    @PatchMapping
    fun updateBank(@RequestBody bank: Bank){
        println("PatchRequest:${bank}")
    }
    @DeleteMapping("/{account}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteBank(@PathVariable account: String) {
        println("DeleteRequest:${account}")
    }
}