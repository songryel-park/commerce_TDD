package com.teamsparta.commerce.exception

class JwtException(
    override var code: ResultCode = ResultCode.UNAUTHORIZED,
    override var message: String
): BaseException()