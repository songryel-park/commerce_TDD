package com.teamsparta.commerce.entity.buy

import com.teamsparta.commerce.entity.User
import com.teamsparta.commerce.entity.sell.Product
import jakarta.persistence.*

@Entity
@Table(name = "bookmark")
class Bookmark(
    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: User,

    @ManyToOne
    @JoinColumn(name = "product_id")
    var product: Product
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var bookmarkId: Long? = null
}