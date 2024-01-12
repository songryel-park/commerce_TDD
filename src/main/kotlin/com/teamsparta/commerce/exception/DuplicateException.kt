package com.teamsparta.commerce.exception

class DuplicateException(message: String): BaseException() {
    override var code: ResultCode = ResultCode.DUPLICATE_ENTITY
    override var message: String = message
}