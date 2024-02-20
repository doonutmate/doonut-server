package com.doonutmate.challenge.service

import com.doonutmate.doonut.challenge.model.Challenge
import com.doonutmate.doonut.challenge.model.ChallengeType
import com.doonutmate.doonut.challenge.service.ChallengeBusinessServicee
import com.doonutmate.image.ImageMeta
import com.doonutmate.image.ImageMetaSupporter
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
import javax.imageio.ImageIO

@Service
class ChallengeService(
    private val service: ChallengeBusinessServicee,
) {

    fun resizeImage(
        fileName: String,
        fileFormatName: String,
        originImage: MultipartFile,
        targetLength: Int,
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

    private fun getThumbnailFileName(filename: String): String {
        return "s_$filename"
    }

    private fun getDefaultFileName(fileName: String): String {
        return "d_$fileName"
    }
}
