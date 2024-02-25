package com.doonutmate.image.service

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.ObjectMetadata
import com.doonutmate.image.controller.dto.ImageUploadResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.UUID

@Service
class ImageService(
    private val amazonS3: AmazonS3,
    private val imageFacadeService: ImageFacadeService,
) {

    @Value("\${cloud.aws.s3.bucket}")
    private val bucket: String? = null

    fun saveFile(multipartFile: MultipartFile, memberId: Long): ImageUploadResponse {
        val randomKey = UUID.randomUUID().toString()

        saveFileToS3(multipartFile, randomKey)
        val imageUrl = saveFileToDb(multipartFile, randomKey, memberId)

        return ImageUploadResponse(imageUrl)
    }

    private fun saveFileToS3(multipartFile: MultipartFile, key: String) {
        val metadata = ObjectMetadata()
        metadata.contentLength = multipartFile.size
        metadata.contentType = multipartFile.contentType

        amazonS3.putObject(bucket, key, multipartFile.inputStream, metadata)

        amazonS3.getUrl(bucket, key).toString()
    }

    private fun saveFileToDb(multipartFile: MultipartFile, key: String, memberId: Long): String {
        return imageFacadeService.save(multipartFile, key, memberId)
    }
}
