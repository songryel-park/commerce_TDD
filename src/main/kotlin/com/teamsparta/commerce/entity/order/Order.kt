package com.teamsparta.commerce.entity.order

import com.teamsparta.commerce.entity.User
import jakarta.persistence.*

@Entity
@Table(name = "order")
class Order(
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    var user: User,

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    var orderLines: MutableList<OrderLine> = ArrayList(),

    var totalPrice: Long = 0,

    @Column(name = "order_line")
    @Enumerated(value = EnumType.STRING)
    var status: OrderStatus
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var orderId: Long? = null

    fun addOrderline(orderLine: OrderLine) {
        this.orderLines.add(orderLine)
    }

    fun cancel() {
        if (status != OrderStatus.PAYMENT_WAITING || status != OrderStatus.DELIVERY_WAITING) {
            throw Exception("결제를 취소할 수 없습니다.")
        }
        this.status = OrderStatus.CANCEL
    }
}