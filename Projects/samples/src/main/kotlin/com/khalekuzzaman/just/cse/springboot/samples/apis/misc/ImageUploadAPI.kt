package com.khalekuzzaman.just.cse.springboot.samples.apis.misc

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/images")
class ImageUploadAPI {

    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.OK)
    fun uploadImage(@RequestParam("image") image: MultipartFile): ResponseEntity<String> {

        println("Received file: ${image.originalFilename} with size: ${image.size} bytes")
        return ResponseEntity("Received ${image.originalFilename} with size: ${image.size} bytes", HttpStatus.OK)
    }
}
