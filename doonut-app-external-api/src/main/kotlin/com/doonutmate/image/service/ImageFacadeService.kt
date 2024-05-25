package com.doonutmate.image.service

import com.doonutmate.doonut.challenge.model.Challenge
import com.doonutmate.doonut.challenge.service.ChallengeBusinessService
import com.doonutmate.doonut.image.model.Image
import com.doonutmate.doonut.image.service.ImageBusinessService
import com.doonutmate.image.ImageMeta
import com.doonutmate.image.ImageMetaSupporter
import com.doonutmate.image.exception.ImageUploadException
import com.doonutmate.util.CommonDateUtils
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

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
        try {
            val lock = challengeBusinessService.getLock(memberId)
            require(lock == 1) { "락을 정상적으로 획득하지 못했습니다. memberId: $memberId" }

            val imageUrl = saveImage(multipartFile, key, memberId)
            deleteChallenge(memberId)
            saveChallenge(memberId, imageUrl)

            return imageUrl
        } finally {
            challengeBusinessService.releaseLock(memberId)
        }
    }

    private fun deleteChallenge(memberId: Long) {
        val challenges = challengeBusinessService.getList(memberId, CommonDateUtils.getToday())
        if (challenges.isEmpty()) {
            return
        }

        validate(challenges)

        val challengeId = challenges[0].id
        challengeBusinessService.delete(challengeId)
    }

    private fun validate(challenges: MutableList<Challenge>) {
        if (challenges.size >= 2) throw ImageUploadException("하루에 하나의 이미지만 업로드할 수 있습니다.")
    }

    fun exposeSaveImage(multipartFile: MultipartFile, key: String, memberId: Long): String {
        return saveImage(multipartFile, key, memberId)
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
