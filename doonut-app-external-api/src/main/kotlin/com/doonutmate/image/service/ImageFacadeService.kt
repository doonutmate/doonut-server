package com.doonutmate.image.service

import com.doonutmate.doonut.challenge.model.Challenge
import com.doonutmate.doonut.challenge.service.ChallengeBusinessService
import com.doonutmate.doonut.image.model.Image
import com.doonutmate.doonut.image.service.ImageBusinessService
import com.doonutmate.image.ImageMeta
import com.doonutmate.image.ImageMetaSupporter
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.time.Instant

@Transactional(readOnly = true)
@Service
class ImageFacadeService(
    private val imageBusinessService: ImageBusinessService,
    private val challengeBusinessService: ChallengeBusinessService,
) {
    @Value("\${cloud.aws.cloudfront.prefix}")
    private val imageHostUrlPrefix: String? = null

    @Transactional
    fun save(multipartFile: MultipartFile, key: String, memberId: Long): String {
        val imageUrl = saveImage(multipartFile, key, memberId)
        deleteChallenge(memberId)
        saveChallenge(memberId, imageUrl)

        return imageUrl
    }

    private fun deleteChallenge(memberId: Long) {
        val challenges = challengeBusinessService.getList(memberId, Instant.now())
        if (challenges.isEmpty()) {
            return
        }

        validate(challenges)

        val challengeId = challenges[0].id
        challengeBusinessService.delete(challengeId)
    }

    private fun validate(challenges: MutableList<Challenge>) {
        if (challenges.size >= 2) throw RuntimeException()
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
