package com.teamsparta.commerce.controller

import com.teamsparta.commerce.request.LoginRequest
import com.teamsparta.commerce.request.SignUpRequest
import com.teamsparta.commerce.service.UserService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {
    @PostMapping("/signUp")
    fun signUp(@Valid @RequestBody request: SignUpRequest): ResponseEntity<String> {
        userService.signUp(request)
        return ResponseEntity("회원가입이 완료되었습니다", HttpStatus.OK)
    }

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest): ResponseEntity<String> {
        userService.login(request)
        return ResponseEntity("로그인 성공", HttpStatus.OK)
    }
}