package com.teamsparta.commerce.entity.sell

import com.teamsparta.commerce.exception.BadRequestException
import jakarta.persistence.*

@Entity
@Table(name = "product")
class Product(
    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    var store: Store,

    @Column(name = "product_name", nullable = false)
    var name: String,

    @Column(name = "category", nullable = false)
    var category: String,

    @Column(name = "product_price", nullable = false)
    var price: Long,

    @Column(name = "description", nullable = false)
    var description: String,

    @Column(name = "product_amount", nullable = false)
    var amount: Long
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var productId: Long? = null

    init {
        store.addProduct(this)
    }

    fun stockLeft(amount: Long){
        if (this.amount < amount) {
            throw BadRequestException("상품 재고가 부족합니다.")
        }
        this.amount -= amount
    }
}