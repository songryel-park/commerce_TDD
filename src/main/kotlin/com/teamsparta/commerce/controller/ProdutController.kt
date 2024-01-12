package com.teamsparta.commerce.controller

import com.teamsparta.commerce.entity.User
import com.teamsparta.commerce.entity.sell.Product
import com.teamsparta.commerce.exception.BadRequestException
import com.teamsparta.commerce.request.ProductRequest
import com.teamsparta.commerce.service.ProductService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ProdutController(private val productService: ProductService) {
    @PostMapping("/products")
    @PreAuthorize("hasRole('ADMIN')")
    fun create(@AuthenticationPrincipal user: User, @Valid @RequestBody request: ProductRequest): ResponseEntity<String> {
        productService.manageProduct(user.username.toLong(), request)
        return ResponseEntity("상품을 생성했습니다.", HttpStatus.OK)
    }

    @PostMapping("/products")
    @PreAuthorize("hasRole('ADMIN')")
    fun update(@AuthenticationPrincipal user: User, @PreAuthorize productId: Long,
               @Valid @RequestBody request: ProductRequest): ResponseEntity<String> {
        if (productId != request.productId!!) {
            throw BadRequestException("해당 상품이 아닙니다.")
        }

        productService.manageProduct(user.username.toLong(), request)
        return ResponseEntity("상품을 수정했습니다.", HttpStatus.OK)
    }
}