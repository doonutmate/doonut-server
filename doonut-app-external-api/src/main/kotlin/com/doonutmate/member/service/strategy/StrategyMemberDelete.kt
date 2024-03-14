package com.doonutmate.member.service.strategy

import com.doonutmate.member.controller.dto.DeleteRequest

interface StrategyMemberDelete {
    fun delete(req: DeleteRequest)
}
