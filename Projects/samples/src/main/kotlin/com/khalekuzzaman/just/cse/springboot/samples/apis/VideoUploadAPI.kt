package com.khalekuzzaman.just.cse.springboot.samples.apis

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/videos")
class VideoUploadAPI {

    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.OK)
    fun uploadImage(@RequestParam("video") video: MultipartFile): ResponseEntity<String> {

        println("Received file: ${video.originalFilename} with size: ${video.size} bytes")
        return ResponseEntity("Received ${video.originalFilename} with size: ${video.size} bytes", HttpStatus.OK)
    }
}
