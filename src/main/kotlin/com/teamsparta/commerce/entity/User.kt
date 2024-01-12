package com.teamsparta.commerce.entity

import jakarta.persistence.*

@Entity
@Table(name = "user")
class User(
    @Column(name = "username", nullable = false)
    var username: String,

    @Column(name = "password", nullable = false)
    var password: String,

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role", nullable = false)
    var role: UserRole,

    @Column(name = "point", nullable = false)
    val point: Long = 0
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var userId: Long? = null

    fun checkAdminOrThrow(){
        if(role != UserRole.ADMIN){
            throw Exception("판매자 권한이 없습니다.")
        }
    }

    fun checkPoint(totalPrice: Long) {
        if (point < totalPrice) {
            throw Exception("잔액이 부족합니다.")
        }
    }
}