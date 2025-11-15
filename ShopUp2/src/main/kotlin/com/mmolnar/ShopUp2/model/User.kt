package com.mmolnar.ShopUp2.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import org.springframework.stereotype.Component

@Component
@Entity
@Table(name = "users", uniqueConstraints = [UniqueConstraint(columnNames = ["username"]), UniqueConstraint(columnNames = ["email"])])
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = 0,
    var username: String = "",
    var email: String = "",
    var password: String = "",
    var name: String = "",
    var role: String = "",
)