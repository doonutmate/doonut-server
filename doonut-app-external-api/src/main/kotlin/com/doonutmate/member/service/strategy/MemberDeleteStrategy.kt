package com.doonutmate.member.service.strategy

import com.doonutmate.doonut.member.model.OauthTypeStrategy
import com.doonutmate.member.controller.dto.DeleteRequest

interface MemberDeleteStrategy {
    val oauthTypeStrategy: OauthTypeStrategy
    fun delete(req: DeleteRequest)
}
