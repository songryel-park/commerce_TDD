package com.teamsparta.commerce.exception

class InvalidInputException(message: String): BaseException() {
    override var code: ResultCode = ResultCode.FORBIDDEN
    override var message: String = message
}