package com.doonutmate.challenge.service

import com.doonutmate.challenge.service.dto.ResizingRequest
import com.doonutmate.doonut.challenge.model.Challenge
import com.doonutmate.doonut.challenge.model.ChallengeType
import com.doonutmate.doonut.challenge.service.ChallengeBusinessServicee
import com.doonutmate.doonut.member.model.OauthType
import com.doonutmate.image.ImageMeta
import com.doonutmate.image.ImageMetaSupporter
import com.doonutmate.image.controller.dto.ImageUploadResponse
import marvin.image.MarvinImage
import org.marvinproject.image.transform.scale.Scale
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
    private val service: ChallengeBusinessServicee,
) {

    fun saveResizingImage(multipartFile: MultipartFile, type: ChallengeType, memberId: String): ImageUploadResponse {
        val randomKey = UUID.randomUUID().toString()

        saveFileToDb(multipartFile, type, memberId)

        return ImageUploadResponse(getImageHostUrl(randomKey))
    }

    fun resizeImage(
        fileName: String,
        fileFormatName: String,
        originImage: MultipartFile,
        type: ChallengeType,
    ): MultipartFile {
        try {
            val image: BufferedImage = ImageIO.read(originImage.inputStream)

            val imageMarvin = MarvinImage(image)

            val scale = Scale()
            scale.load()
            scale.setAttribute("newWidth", targetLength)
            scale.setAttribute("newHeight", targetLength)
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

    private fun saveFileToDb(multipartFile: MultipartFile, type: ChallengeType, memberId: String): Long {
        val imageMeta: ImageMeta = ImageMetaSupporter.extract(multipartFile)
        val newChallenge = Challenge.builder()
            .type(type)
            .imageUrl(multipartFile.originalFilename)
            .memberId(memberId)
            .deleted(false)
            .build()

        return service.create(newChallenge)
    }

    private fun validLengthAndName(type: ChallengeType, req: ResizingRequest): ResizingRequest {
        return when (type) {
            ChallengeType.DEFAULT -> {
                ResizingRequest(getDefaultFileName(req.fileName), DEFAULT_LENGTH)
            }
            ChallengeType.THUMBNAIL -> {
                ResizingRequest(getThumbnailFileName(req.fileName), THUMBNAIL_LENGTH)
            }
        }
    }

    private fun getThumbnailFileName(filename: String): String {
        return "s_$filename"
    }

    private fun getDefaultFileName(fileName: String): String {
        return "d_$fileName"
    }

    companion object {
        const val DEFAULT_LENGTH = 300
        const val THUMBNAIL_LENGTH = 68
    }
}
