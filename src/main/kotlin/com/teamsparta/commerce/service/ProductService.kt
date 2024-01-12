package com.teamsparta.commerce.service

import com.teamsparta.commerce.request.ProductRequest
import com.teamsparta.commerce.entity.User
import com.teamsparta.commerce.entity.sell.Product
import com.teamsparta.commerce.entity.sell.Store
import com.teamsparta.commerce.exception.BadRequestException
import com.teamsparta.commerce.exception.NotFoundException
import com.teamsparta.commerce.repository.ProductRepository
import com.teamsparta.commerce.repository.StoreRepository
import com.teamsparta.commerce.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductService(
    private val storeRepository: StoreRepository,
    private val productRepository: ProductRepository,
    private val userRepository: UserRepository,
) {
    @Transactional
    fun createProduct(userId: Long, request: ProductRequest): ProductRequest {
        val user: User = userRepository.findById(userId)
            .orElseThrow { NotFoundException("존재하지 않는 계정 입니다.") }

        val store: Store = storeRepository.findById(request.storeId)
            .orElseThrow { NotFoundException("존재하지 않는 상점 입니다.") }

        checkValidRelation(user, store)

        val newProduct: Product = buildProduct(store, request)

        return productRepository.save(newProduct).toResponse()
    }

    private fun buildProduct(store: Store, request: ProductRequest): Product {
        return Product(
            store = store,
            name = request.name,
            category = request.category,
            price = request.price,
            description = request.description,
            stock = request.stock
        )
    }

    private fun checkValidRelation(user: User, store: Store){
        if (user.userId != store.user.userId) {
            throw BadRequestException("본인의 상품만 등록가능")
        }
    }
}