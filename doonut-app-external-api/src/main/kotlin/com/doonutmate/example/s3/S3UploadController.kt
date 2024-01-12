package com.doonutmate.example.s3

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
class S3UploadController(
    private val s3UploadService: S3UploadService,
) {
    @PostMapping("/upload")
    fun testUpload(@RequestPart("multipartFile") multipartFile: MultipartFile) {
        s3UploadService.saveFile(multipartFile)
    }
}
