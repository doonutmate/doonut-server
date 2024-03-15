package com.doonutmate.member.controller.dto

import com.doonutmate.doonut.member.model.MemberDeleteOauthType

data class DeleteRequest(val memberId: Long, val code: String, val memberDeleteOauthType: MemberDeleteOauthType)
