package com.teamsparta.commerce.repository

import com.teamsparta.commerce.entity.order.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository: JpaRepository<Order, Long> {}