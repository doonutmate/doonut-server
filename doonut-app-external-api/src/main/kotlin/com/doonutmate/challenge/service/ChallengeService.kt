package com.doonutmate.challenge.service

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.ObjectMetadata
import com.doonutmate.challenge.controller.dto.ChallengeListRequest
import com.doonutmate.challenge.controller.dto.ChallengeListResponse
import com.doonutmate.doonut.challenge.model.Challenge
import com.doonutmate.doonut.challenge.model.ChallengeType
import com.doonutmate.doonut.challenge.service.ChallengeBusinessServicee
import com.doonutmate.image.controller.dto.ImageUploadResponse
import marvin.image.MarvinImage
import org.marvinproject.image.transform.scale.Scale
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.mock.web.MockMultipartFile
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.server.ResponseStatusException
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.util.UUID
import javax.imageio.ImageIO

@Service
class ChallengeService(
    private val amazonS3: AmazonS3,
    private val service: ChallengeBusinessServicee,
) {

    @Value("\${cloud.aws.cloudfront.prefix}")
    private val imageHostUrlPrefix: String? = null

    @Value("\${cloud.aws.s3.bucket}")
    private val bucket: String? = null

    fun getChallengeList(
        memberId: String,
        req: ChallengeListRequest,
    ): List<ChallengeListResponse> {
        val arr = service.getAllByIdAndDate(memberId, req.year, req.month)
        val transformedList: List<ChallengeListResponse> = arr.map { challenge ->
            ChallengeListResponse(
                imageUrl = challenge.imageUrl,
                day = challenge.created_at.toString().substring(DAYS_START, DAYS_END),
            )
        }
        return transformedList
    }

    fun saveResizingImage(
        multipartFile: MultipartFile,
        type: ChallengeType,
        memberId: String,
    ): ImageUploadResponse {
        val randomKey = UUID.randomUUID().toString()
        val resizingImage = resizeImage(randomKey, getFileFormatName(multipartFile), multipartFile, type)
        saveFileToDb(type, memberId, randomKey)
        saveFileToS3(resizingImage, randomKey)

        return ImageUploadResponse(getImageHostUrl(randomKey))
    }

    private fun saveFileToS3(multipartFile: MultipartFile, key: String): String {
        val metadata = ObjectMetadata()
        metadata.contentLength = multipartFile.size
        metadata.contentType = multipartFile.contentType

        amazonS3.putObject(bucket, key, multipartFile.inputStream, metadata)

        return amazonS3.getUrl(bucket, key).toString()
    }

    private fun saveFileToDb(
        type: ChallengeType,
        memberId: String,
        key: String,
    ): Long {
        val newChallenge = Challenge.builder()
            .type(type)
            .imageUrl(getImageHostUrl(key))
            .memberId(memberId)
            .deleted(false)
            .build()

        return service.create(newChallenge)
    }

    private fun resizeImage(
        fileName: String,
        fileFormatName: String,
        originImage: MultipartFile,
        type: ChallengeType,
    ): MultipartFile {
        try {
            val image: BufferedImage = ImageIO.read(originImage.inputStream)
            val length = branchedLength(type)
            val imageMarvin = MarvinImage(image)

            val scale = Scale()
            scale.load()
            scale.setAttribute("newWidth", length)
            scale.setAttribute("newHeight", length)
            scale.process(imageMarvin.clone(), imageMarvin, null, null, false)

            val imageNoAlpha: BufferedImage = imageMarvin.bufferedImageNoAlpha
            val baos = ByteArrayOutputStream()
            ImageIO.write(imageNoAlpha, fileFormatName, baos)
            baos.flush()

            return MockMultipartFile(fileName, baos.toByteArray())
        } catch (e: IOException) {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 리사이즈에 실패했습니다.")
        }
    }

    private fun branchedLength(type: ChallengeType): Int {
        return when (type) {
            ChallengeType.DEFAULT -> {
                DEFAULT_LENGTH
            }

            ChallengeType.THUMBNAIL -> {
                THUMBNAIL_LENGTH
            }
        }
    }

    private fun getImageHostUrl(key: String) = imageHostUrlPrefix + key

    private fun getFileFormatName(file: MultipartFile): String {
        return file.contentType!!.substring(file.contentType!!.lastIndexOf("/") + 1)
    }

    companion object {
        const val DEFAULT_LENGTH = 390
        const val THUMBNAIL_LENGTH = 46
        const val DAYS_START = 8
        const val DAYS_END = 10
    }
}
