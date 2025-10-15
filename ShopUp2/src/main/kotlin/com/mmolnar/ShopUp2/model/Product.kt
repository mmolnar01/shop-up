package com.mmolnar.ShopUp2.model

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Lob
import jakarta.persistence.Table
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.util.Date

@Component
@Entity
@Table(name = "products")
data class Product(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,
    val name: String? = null,
    val description: String? = null,
    val brand: String? = null,
    val price: Double = 0.0,
    val category: String? = null,

    val releaseDate: Date? = null,
    val productAvailable: Boolean = false,
    val stockQuantity: Int = 0,

    var imageName: String? = null,
    var imageType: String = "",
    @Lob
    var imageData: ByteArray? = null
)
