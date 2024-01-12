package com.teamsparta.commerce.exception

import lombok.Getter
import lombok.Setter

@Getter
@Setter
data class ErrorResponse(
    var code: ResultCode? = null,
    var message: String? = null
)