package com.doonutmate.image.service

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.ObjectMetadata
import com.doonutmate.image.ImageMetaSupporter
import com.doonutmate.image.controller.dto.ImageUploadResponse
import com.doonutmate.util.CommonDateUtils
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.time.Instant
import java.util.UUID

private const val FOLDER_DELIMITER = "/"
private const val FILE_EXTENSION_DELIMITER = "."

@Service
class ImageAppService(
    private val amazonS3: AmazonS3,
    private val imageFacadeService: ImageFacadeService,
) {

    @Value("\${cloud.aws.s3.bucket}")
    private val bucket: String? = null

    @Value("\${spring.profiles.active:local}")
    private val activeProfile: String? = null

    fun uploadImage(multipartFile: MultipartFile, memberId: Long): ImageUploadResponse {
        val randomKey = resolveObjectKey(multipartFile)

        saveImageToS3(multipartFile, randomKey)
        val imageUrl = saveImageAndChallengeToDb(multipartFile, randomKey, memberId)

        return ImageUploadResponse(imageUrl)
    }

    private fun saveImageToS3(multipartFile: MultipartFile, key: String) {
        val metadata = ObjectMetadata()
        metadata.contentLength = multipartFile.size
        metadata.contentType = multipartFile.contentType

        amazonS3.putObject(bucket, key, multipartFile.inputStream, metadata)

        amazonS3.getUrl(bucket, key).toString()
    }

    private fun saveImageAndChallengeToDb(multipartFile: MultipartFile, key: String, memberId: Long): String {
        return imageFacadeService.saveImageAndChallenge(multipartFile, key, memberId)
    }

    fun saveProfileImage(multipartFile: MultipartFile, memberId: Long): ImageUploadResponse {
        val randomKey = resolveObjectKey(multipartFile)

        saveImageToS3(multipartFile, randomKey)
        val imageUrl = saveProfileImageToDb(multipartFile, randomKey, memberId)

        return ImageUploadResponse(imageUrl)
    }

    private fun saveProfileImageToDb(multipartFile: MultipartFile, key: String, memberId: Long): String {
        return imageFacadeService.saveImage(multipartFile, key, memberId)
    }

    private fun resolveObjectKey(multipartFile: MultipartFile): String {
        val randomUUID = UUID.randomUUID().toString()
        val extension = ImageMetaSupporter.getExtension(multipartFile.originalFilename)
        val date = CommonDateUtils.getYearMonthDay(Instant.now())

        return activeProfile + FOLDER_DELIMITER + date + FOLDER_DELIMITER + randomUUID + FILE_EXTENSION_DELIMITER + extension
    }
}
