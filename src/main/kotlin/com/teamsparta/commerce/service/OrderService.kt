package com.teamsparta.commerce.service

import com.teamsparta.commerce.request.OrderRequest
import com.teamsparta.commerce.entity.User
import com.teamsparta.commerce.entity.order.Order
import com.teamsparta.commerce.entity.order.OrderLine
import com.teamsparta.commerce.entity.order.OrderStatus
import com.teamsparta.commerce.entity.sell.Product
import com.teamsparta.commerce.exception.BadRequestException
import com.teamsparta.commerce.exception.NotFoundException
import com.teamsparta.commerce.repository.OrderLineRepository
import com.teamsparta.commerce.repository.OrderRepository
import com.teamsparta.commerce.repository.ProductRepository
import com.teamsparta.commerce.repository.UserRepository
import com.teamsparta.commerce.request.OrderLineRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderService(
    private val productRepository: ProductRepository,
    private val orderRepository: OrderRepository,
    private val orderLineRepository: OrderLineRepository,
    private val userRepository: UserRepository,
) {
    @Transactional
    fun createOrder(userId: Long, request: OrderRequest): Order {
        val user: User = userRepository.findById(userId)
            .orElseThrow{ NotFoundException("존재하지 않는 계정 입니다.")}

        val order = Order(user = user, status = OrderStatus.PAYMENT_WAITING)
        orderRepository.save(order)

        val productList: List<Product> = productRepository.findProductsById(request.productRequestList.keys)
        val orderList = buildOrderList(order, request, productList)
        orderLineRepository.saveAll(orderList)

        user.checkPoint(order.totalPrice)
        return order
    }

    @Transactional
    fun buy(userId: Long, orderId: Long): Order {
        val user = userRepository.findById(userId)
            .orElseThrow{ NotFoundException("존재하지 않는 계정 입니다.")}

        val order = orderRepository.findById(orderId)
            .orElseThrow{ NotFoundException("주문내역을 찾을 수 없습니다.")}

        return order
    }

    private fun buildOrderList(order: Order, request: OrderRequest, productList: List<Product>): MutableList<OrderLine> {
        if (request.productRequestList.size != productList.size) {
            throw BadRequestException("잘못된 상품이 존재합니다.")
        }
        return productList.map {
            val requestAmount = request.productRequestList[it.productId]!!
            it.stockLeft(requestAmount)
            order.totalPrice += it.price

            OrderLine(order = order, product = it, amount = requestAmount)
        }.toMutableList()
    }
}