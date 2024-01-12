package com.teamsparta.commerce.entity.order

import com.teamsparta.commerce.entity.sell.Product
import jakarta.persistence.*

@Entity
@Table(name = "order_line")
class OrderLine(
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    var order: Order,

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    var product: Product,

    @Column(name = "amount", nullable = false)
    var amount: Long
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var orderLineId: Long? = null

    init{
        order.addOrderline(this)
    }
}