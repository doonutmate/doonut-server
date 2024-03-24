package com.doonutmate.image.controller.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(title = "이미지 업로드 응답")
data class ImageUploadResponse(
    @Schema(title = "이미지 url")
    val imageUrl: String,
)
