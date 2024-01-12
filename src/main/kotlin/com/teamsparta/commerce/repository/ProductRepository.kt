package com.teamsparta.commerce.repository

import com.teamsparta.commerce.entity.Category
import com.teamsparta.commerce.entity.sell.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository: JpaRepository<Product, Long> {
    fun findProductsById(keys: Set<Long>): MutableList<Product>
    fun findByCategory(category: Category): List<Product>
}