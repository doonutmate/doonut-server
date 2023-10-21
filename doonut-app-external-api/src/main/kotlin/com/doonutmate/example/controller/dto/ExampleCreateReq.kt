package com.doonutmate.example.controller.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "example 생성용 DTO")
data class ExampleCreateReq(
    @Schema(description = "이름")
    val name: String,
)
