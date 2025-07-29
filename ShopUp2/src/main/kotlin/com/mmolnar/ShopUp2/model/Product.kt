package com.mmolnar.ShopUp2.model

import org.springframework.stereotype.Component

@Component
data class Product(
    val prodId: Int = 0,
    val prodName: String? = null,
    val price: Int = 0
)
