package com.doonutmate.example.s3

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@Tag(name = "S3 업로드 example", description = "S3 업로드 테스트용 API")
class S3UploadController(
    private val s3UploadService: S3UploadService,
) {
    @PostMapping(
        value = ["/upload"],
        consumes = [MediaType.MULTIPART_FORM_DATA_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE],
    )
    fun testUpload(@RequestPart("multipartFile") multipartFile: MultipartFile) {
        s3UploadService.saveFile(multipartFile)
    }
}
