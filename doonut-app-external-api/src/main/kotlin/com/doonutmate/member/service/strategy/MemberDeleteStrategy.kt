package com.doonutmate.member.service.strategy

import com.doonutmate.member.controller.dto.DeleteRequest

interface MemberDeleteStrategy {
    fun delete(req: DeleteRequest)
}
