package com.doonutmate.challenge.service

import com.doonutmate.challenge.controller.dto.ChallengeListRequest
import com.doonutmate.challenge.controller.dto.ChallengeListResponse
import com.doonutmate.doonut.challenge.model.ChallengeType
import com.doonutmate.doonut.challenge.service.ChallengeBusinessService
import com.doonutmate.util.CommonDateUtils
import org.springframework.stereotype.Service

@Service
class ChallengeService(
    private val service: ChallengeBusinessService,
) {

    fun getChallengeList(memberId: Long, req: ChallengeListRequest): List<ChallengeListResponse> {
        val challenges = service.getAllByIdAndDate(memberId, req.year, req.month)
        return challenges.map { challenge ->
            ChallengeListResponse(
                defaultUrl = ChallengeType.getDefaultUrl(challenge.imageUrl),
                thumbNailUrl = ChallengeType.getThumbNailUrl(challenge.imageUrl),
                day = CommonDateUtils.getDay(challenge.createdAt),
            )
        }
    }
}
