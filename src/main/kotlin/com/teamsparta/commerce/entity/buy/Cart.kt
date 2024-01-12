package com.teamsparta.commerce.entity.buy

import com.teamsparta.commerce.entity.User
import com.teamsparta.commerce.entity.sell.Product
import jakarta.persistence.*

@Entity
@Table(name = "cart")
class Cart(
    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: User,

    @ManyToOne
    @JoinColumn(name = "product_id")
    var product: Product,

    @Column(name = "product_quantity", nullable = false)
    var quantity: Long
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var cartId: Long? = null
}