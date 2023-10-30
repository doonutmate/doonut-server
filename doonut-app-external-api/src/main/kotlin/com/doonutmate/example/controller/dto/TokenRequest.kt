package com.doonutmate.example.controller.dto

import jakarta.validation.constraints.NotEmpty

data class TokenRequest(
    @NotEmpty
    val accessToken: String
)
