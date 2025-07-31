package com.mmolnar.ShopUp2.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import org.springframework.stereotype.Component

@Component
@Entity
data class Product(

    @Id
    val prodId: Int = 0,
    val prodName: String? = null,
    val price: Int = 0
)
