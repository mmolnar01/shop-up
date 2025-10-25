package com.mmolnar.ShopUp2.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank

data class LoginRequest(
    @Schema(example = "user") @NotBlank var username: String,
    @Schema(example = "user") @NotBlank var password: String,
)