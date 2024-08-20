package com.doonutmate.image.service

import com.doonutmate.calendar.exception.CalendarException
import com.doonutmate.doonut.calendar.service.CalendarBusinessService
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
    private val calendarBusinessService: CalendarBusinessService,
) {
    @Value("\${cloud.aws.cloudfront.prefix}")
    private val imageHostUrlPrefix: String? = null

    @Transactional
    fun saveImageAndChallenge(multipartFile: MultipartFile, key: String, memberId: Long): String {
        try {
            val lock = challengeBusinessService.getLock(memberId)
            require(lock == 1) { "락을 정상적으로 획득하지 못했습니다. memberId: $memberId" }

            val imageUrl = saveImage(multipartFile, key, memberId)
            deleteChallenge(memberId)

            val challengeId = saveChallenge(memberId, imageUrl)
            updateCalendar(memberId, challengeId)

            return imageUrl
        } finally {
            challengeBusinessService.releaseLock(memberId)
        }
    }

    fun saveImage(multipartFile: MultipartFile, key: String, memberId: Long): String {
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

    private fun saveChallenge(memberId: Long, imageUrl: String): Long {
        val newChallenge = Challenge.builder()
            .memberId(memberId)
            .imageUrl(imageUrl)
            .deleted(false)
            .build()
        return challengeBusinessService.create(newChallenge)
    }

    private fun updateCalendar(memberId: Long, challengeId: Long) {
        val calendar = calendarBusinessService.getByMemberId(memberId)
            ?: throw CalendarException("캘린더를 찾을 수 없습니다. memberId: $memberId")

        val createdAt = challengeBusinessService.get(challengeId).createdAt
        val updatedCalendar = calendar.toUpdated(createdAt)

        calendarBusinessService.update(updatedCalendar)
    }

    private fun getImageUrl(key: String) = imageHostUrlPrefix + key
}
