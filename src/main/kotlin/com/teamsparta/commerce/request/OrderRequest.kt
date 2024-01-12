package com.teamsparta.commerce.request

import jakarta.validation.constraints.NotNull

data class OrderRequest(var productRequestList: Map<Long, Long>)

data class OrderLineRequest(
    @field:NotNull
    var productId: Long,
    @field:NotNull
    var amount: Long
)