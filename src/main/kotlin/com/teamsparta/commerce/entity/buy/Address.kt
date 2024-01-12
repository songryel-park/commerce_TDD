package com.teamsparta.commerce.entity.buy

import com.teamsparta.commerce.entity.User
import jakarta.persistence.*

@Entity
class Address(
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    var user: User,

    @Column(name = "address")
    var address: String,

    @Column(name = "postal_code")
    var postalCode: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val addressId: Long? = null
}