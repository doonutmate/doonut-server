package com.doonutmate.image.service

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.ObjectMetadata
import com.doonutmate.image.controller.dto.ImageUploadResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class ImageService(
    private val amazonS3: AmazonS3,
) {

    @Value("\${cloud.aws.s3.bucket}")
    private val bucket: String? = null

    fun saveFile(multipartFile: MultipartFile): ImageUploadResponse {
        val originalFilename = multipartFile.originalFilename
        val metadata = ObjectMetadata()
        metadata.setContentLength(multipartFile.size)
        metadata.setContentType(multipartFile.contentType)

        amazonS3.putObject(bucket, originalFilename, multipartFile.inputStream, metadata)
        val imageUrl = amazonS3.getUrl(bucket, originalFilename).toString()

        return ImageUploadResponse(imageUrl)
    }
}
