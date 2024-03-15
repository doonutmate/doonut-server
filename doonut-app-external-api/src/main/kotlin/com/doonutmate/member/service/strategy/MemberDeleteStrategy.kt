package com.doonutmate.member.service.strategy

import com.doonutmate.doonut.member.model.MemberDeleteOauthType
import com.doonutmate.member.controller.dto.DeleteRequest

interface MemberDeleteStrategy {
    val memberDeleteOauthType: MemberDeleteOauthType
    fun delete(req: DeleteRequest)
}
