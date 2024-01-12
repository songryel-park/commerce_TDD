package com.teamsparta.commerce.entity.sell

import com.teamsparta.commerce.entity.User
import jakarta.persistence.*

@Entity
class Store(
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @Column(name = "store_account", nullable = false)
    var account: Long,

    @OneToMany(mappedBy = "store")
    var productList: MutableList<Product> = ArrayList()
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val storeId: Long? = null

    fun addProduct(product: Product) {
        productList.add(product)
    }
}