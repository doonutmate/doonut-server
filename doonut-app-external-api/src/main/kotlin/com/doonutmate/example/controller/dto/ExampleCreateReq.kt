package com.doonutmate.example.controller.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(title = "example 생성용 DTO")
data class ExampleCreateReq(
    @Schema(title = "이름", example = "jickchan")
    var name: String,
)
