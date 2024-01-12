package com.teamsparta.commerce.entity.order

enum class OrderStatus(val value: String) {
    PAYMENT_WAITING("결제대기"),
    COMPLETE("주문완료"),
    DELIVERY_WAITING("발송대기"),
    DELIVERING("배송중"),
    CANCEL("주문취소")
}