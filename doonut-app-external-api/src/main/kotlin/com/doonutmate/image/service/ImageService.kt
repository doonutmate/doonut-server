package com.doonutmate.image.service

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.ObjectMetadata
import com.doonutmate.doonut.challenge.model.Challenge
import com.doonutmate.doonut.challenge.service.ChallengeBusinessServicee
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
    private val challengeBusinessService: ChallengeBusinessServicee,
) {

    @Value("\${cloud.aws.s3.bucket}")
    private val bucket: String? = null

    @Value("\${cloud.aws.cloudfront.prefix}")
    private val imageHostUrlPrefix: String? = null

    fun saveFile(multipartFile: MultipartFile, memberId: Long): ImageUploadResponse {
        val randomKey = UUID.randomUUID().toString()

        saveFileToS3(multipartFile, randomKey)
        saveFileToDb(multipartFile, randomKey, memberId)

        return ImageUploadResponse(getImageUrl(randomKey))
    }

    private fun saveFileToS3(multipartFile: MultipartFile, key: String) {
        val metadata = ObjectMetadata()
        metadata.contentLength = multipartFile.size
        metadata.contentType = multipartFile.contentType

        amazonS3.putObject(bucket, key, multipartFile.inputStream, metadata)

        amazonS3.getUrl(bucket, key).toString()
    }

    private fun saveFileToDb(multipartFile: MultipartFile, key: String, memberId: Long) {
        val imageUrl = saveImage(multipartFile, key, memberId)
        saveChallenge(memberId, imageUrl)
    }

    private fun saveImage(multipartFile: MultipartFile, key: String, memberId: Long): String {
        val imageMeta: ImageMeta = ImageMetaSupporter.extract(multipartFile)
        val imageUrl = getImageUrl(key)
        val newImage = Image.builder()
            .imageKey(key)
            .oriImageName(multipartFile.originalFilename)
            .imageHostUrl(imageUrl)
            .height(imageMeta.height)
            .width(imageMeta.widht)
            .capacity(imageMeta.capacity)
            .memberId(memberId)
            .deleted(false)
            .build()
        imageBusinessService.create(newImage)
        return imageUrl
    }

    private fun saveChallenge(memberId: Long, imageUrl: String) {
        val newChallenge = Challenge.builder()
            .memberId(memberId)
            .imageUrl(imageUrl)
            .deleted(false)
            .build()
        challengeBusinessService.create(newChallenge)
    }

    private fun getImageUrl(key: String) = imageHostUrlPrefix + key
}
