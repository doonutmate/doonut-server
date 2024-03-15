package com.doonutmate.member.controller.dto

import com.doonutmate.doonut.member.model.OauthTypeStrategy

data class DeleteRequest(val memberId: Long, val code: String, val oauthTypeStrategy: OauthTypeStrategy)
