package com.teamsparta.commerce.repository

import com.teamsparta.commerce.entity.order.OrderLine
import org.springframework.data.jpa.repository.JpaRepository

interface OrderLineRepository: JpaRepository<OrderLine, Long>