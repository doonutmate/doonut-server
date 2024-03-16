package com.doonutmate.member.service.strategy

import com.doonutmate.doonut.member.model.OauthType
import com.doonutmate.member.controller.dto.DeleteRequest

interface MemberDeleteStrategy {
    val oauthType: OauthType
    fun delete(req: DeleteRequest)
}
