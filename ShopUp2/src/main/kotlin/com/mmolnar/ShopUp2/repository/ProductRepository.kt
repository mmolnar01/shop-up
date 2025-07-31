package com.mmolnar.ShopUp2.repository

import com.mmolnar.ShopUp2.model.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<Product, Int> {

}