package com.doonutmate.challenge.service

import com.doonutmate.challenge.controller.dto.ChallengeListRequest
import com.doonutmate.challenge.controller.dto.ChallengeListResponse
import com.doonutmate.doonut.challenge.model.ChallengeType
import com.doonutmate.doonut.challenge.service.ChallengeBusinessService
import org.springframework.stereotype.Service

@Service
class ChallengeService(
    private val service: ChallengeBusinessService,
) {

    fun getChallengeList(memberId: Long, req: ChallengeListRequest): List<ChallengeListResponse> {
        ChallengeType.THUMBNAIL.width
        ChallengeType.THUMBNAIL.height

        val arr = service.getAllByIdAndDate(memberId, req.year, req.month)
        val transformedList: List<ChallengeListResponse> = arr.map { challenge ->
            ChallengeListResponse(
                imageUrl = challenge.imageUrl,
                day = challenge.createdAt.toString().substring(DAYS_START, DAYS_END),
            )
        }
        return transformedList
    }

    companion object {
        const val DAYS_START = 8
        const val DAYS_END = 10
    }
}
