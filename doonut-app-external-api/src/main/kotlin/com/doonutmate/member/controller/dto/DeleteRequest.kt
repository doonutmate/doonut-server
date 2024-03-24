package com.doonutmate.member.controller.dto

import com.doonutmate.doonut.member.model.OauthType

data class DeleteRequest(val memberId: Long, val code: String?, val oauthType: OauthType)
