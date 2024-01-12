package com.teamsparta.commerce.entity.buy

import com.teamsparta.commerce.entity.User
import com.teamsparta.commerce.entity.sell.Product
import jakarta.persistence.*
import org.hibernate.validator.internal.constraintvalidators.bv.money.CurrencyValidatorForMonetaryAmount

@Entity
@Table(name = "cart")
class Cart(
    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: User,

    @ManyToOne
    @JoinColumn(name = "product_id")
    var product: Product,

    @Column(name = "amount", nullable = false)
    var amount: Long
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var cartId: Long? = null
}