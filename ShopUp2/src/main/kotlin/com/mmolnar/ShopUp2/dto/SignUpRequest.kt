package com.mmolnar.ShopUp2.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email

data class SignUpRequest(
    @Schema(example = "user3") var username: String,
    @Schema(example = "user3") var password: String,
    @Schema(example = "User3") var name: String,
    @Schema(example = "user3@mycompany.com") @Email var email: String,
    )