package com.teamsparta.commerce.request

import com.fasterxml.jackson.annotation.JsonProperty
import com.teamsparta.commerce.entity.UserRole
import jakarta.validation.constraints.NotBlank

data class SignUpRequest(
    var userId: Long?,

    @field:NotBlank
    @JsonProperty("username")
    var username: String,

    @field:NotBlank
    @JsonProperty("password")
    var password: String,

    @field:NotBlank
    @JsonProperty("role")
    var role: UserRole,

    @JsonProperty("point")
    var point: Long = 0
)