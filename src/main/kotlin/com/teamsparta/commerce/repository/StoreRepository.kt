package com.teamsparta.commerce.repository

import com.teamsparta.commerce.entity.sell.Store
import org.springframework.data.jpa.repository.JpaRepository

interface StoreRepository: JpaRepository<Store, Long> {
    fun findStoresByUserId(userId: Long): MutableList<Store>
}