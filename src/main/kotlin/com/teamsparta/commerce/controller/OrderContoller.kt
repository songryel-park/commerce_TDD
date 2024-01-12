package com.teamsparta.commerce.controller

import com.teamsparta.commerce.entity.User
import com.teamsparta.commerce.request.OrderRequest
import com.teamsparta.commerce.service.OrderService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderContoller(private val orderService: OrderService) {
    @PostMapping("/orders")
    fun create(@AuthenticationPrincipal user: User, @Valid @RequestBody request: OrderRequest): ResponseEntity<String> {
        orderService.createOrder(user.username.toLong(), request)
        return ResponseEntity("장바구니에 담았습니다.", HttpStatus.OK)
    }

    @PostMapping("/orders/{orderId}/buy")
    fun buy(@AuthenticationPrincipal user: User, @PathVariable orderId: Long): ResponseEntity<String> {
        orderService.buy(user.username.toLong(), orderId)
        return ResponseEntity("주문이 완료되었습니다.", HttpStatus.OK)
    }
}