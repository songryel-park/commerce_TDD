package com.teamsparta.commerce.service

import com.teamsparta.commerce.request.LoginRequest
import com.teamsparta.commerce.request.SignUpRequest
import com.teamsparta.commerce.entity.User
import com.teamsparta.commerce.exception.DuplicateException
import com.teamsparta.commerce.repository.UserRepository
import com.teamsparta.commerce.security.JwtTokenProvider
import com.teamsparta.commerce.security.TokenInfo
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val authenticationManagerBuilder: AuthenticationManagerBuilder,
    private val jwtTokenProvider: JwtTokenProvider,
    private val passwordEncoder: PasswordEncoder
) {
    fun signUp(request: SignUpRequest): User {
        val result = userRepository.existsByUsername(request.username)
        if (result) {
            throw DuplicateException("중복된 ID입니다")
        }

        val user = User(
            username = request.username,
            password = passwordEncoder.encode(request.password),
            role = request.role,
            point = request.point
        )
        return userRepository.save(user)
    }

    fun login(request: LoginRequest): TokenInfo {
        val authenticationToken = UsernamePasswordAuthenticationToken(request.username, request.password)
        val authentication = authenticationManagerBuilder.`object`.authenticate(authenticationToken)

        return jwtTokenProvider.createToken(authentication)
    }
}