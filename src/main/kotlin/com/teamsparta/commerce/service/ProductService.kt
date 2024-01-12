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
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductService(
    private val storeRepository: StoreRepository,
    private val productRepository: ProductRepository,
    private val userRepository: UserRepository,
) {
    @Transactional
    fun manageProduct(userId: Long, request: ProductRequest): Product {
        val user: User = userRepository.findById(userId)
            .orElseThrow { NotFoundException("존재하지 않는 계정입니다.") }

        val store: Store = storeRepository.findById(request.storeId)
            .orElseThrow { NotFoundException("존재하지 않는 상점입니다.") }

        checkValidRelation(user, store)

        return if (request.productId == null) create(request, store) else update(request, store)
    }

    private fun create(request: ProductRequest, store: Store): Product {
        val product = buildProduct(request, store)
        return productRepository.save(product)
    }

    private fun update(request: ProductRequest, store: Store): Product {
        val product = productRepository.findById(request.productId!!)
            .orElseThrow { NotFoundException("존재하지 않는 상품입니다.")}
        product.update(buildProduct(request, store))

        return productRepository.save(product)
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