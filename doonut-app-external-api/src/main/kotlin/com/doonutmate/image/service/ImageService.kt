package com.doonutmate.image.service

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.ObjectMetadata
import com.doonutmate.doonut.image.model.Image
import com.doonutmate.doonut.image.service.ImageBusinessService
import com.doonutmate.image.ImageMeta
import com.doonutmate.image.ImageMetaSupporter
import com.doonutmate.image.controller.dto.ImageUploadResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.UUID

@Service
class ImageService(
    private val amazonS3: AmazonS3,
    private val imageBusinessService: ImageBusinessService,
) {

    @Value("\${cloud.aws.s3.bucket}")
    private val bucket: String? = null

    fun saveFile(multipartFile: MultipartFile): ImageUploadResponse {
        val randomKey = UUID.randomUUID().toString()
        val imageUrl = saveFileToS3(multipartFile, randomKey)

        saveFileToDb(multipartFile, randomKey, imageUrl)

        return ImageUploadResponse(imageUrl)
    }

    private fun saveFileToS3(multipartFile: MultipartFile, key: String): String {
        val metadata = ObjectMetadata()
        metadata.contentLength = multipartFile.size
        metadata.contentType = multipartFile.contentType

        amazonS3.putObject(bucket, key, multipartFile.inputStream, metadata)

        return amazonS3.getUrl(bucket, key).toString()
    }

    private fun saveFileToDb(multipartFile: MultipartFile, key: String, imageUrl: String): Long {
        val imageMeta: ImageMeta = ImageMetaSupporter.extract(multipartFile)
        val newImage = Image.builder()
            .imageKey(key)
            .oriImageName(multipartFile.originalFilename)
            .imageHostUrl(imageUrl)
            .height(imageMeta.height)
            .width(imageMeta.widht)
            .capacity(imageMeta.capacity)
            .deleted(false)
            .build()
        return imageBusinessService.create(newImage)
    }
}
