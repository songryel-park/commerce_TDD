package com.teamsparta.commerce.request

import com.teamsparta.commerce.entity.Category
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class ProductRequest(
    val productId: Long?,

    @field:NotNull
    val storeId: Long,

    @field:NotBlank
    val name: String,

    @field:NotNull
    val category: Category,

    var description: String,

    @field:NotNull
    val price: Long,

    @field:NotNull
    val stock: Long
)