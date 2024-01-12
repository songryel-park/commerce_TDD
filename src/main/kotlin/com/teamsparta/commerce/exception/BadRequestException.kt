package com.teamsparta.commerce.exception

class BadRequestException(message: String): BaseException() {
    override var code: ResultCode = ResultCode.BAD_REQUEST
    override var message: String = message
}