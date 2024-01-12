package com.teamsparta.commerce.exception

abstract class BaseException: RuntimeException() {
    open lateinit var code: ResultCode
    override lateinit var message: String
}