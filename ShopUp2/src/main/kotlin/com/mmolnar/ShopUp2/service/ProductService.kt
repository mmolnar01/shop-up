package com.mmolnar.ShopUp2.service

import com.mmolnar.ShopUp2.model.Product
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable

@Service
class ProductService {

    val products = mutableListOf<Product>(
        Product(101, "Galaxy S25", 1000),
        Product(102, "Bathtub", 800),
        Product(103, "Picture Frame", 20)
    )

    fun getProductsList(): List<Product> {
        return products
    }

    fun getProductById(id: Int): Product {
        return products.stream()
            .filter { p -> p.prodId == id }
            .findFirst()
            .orElse(Product(100, "No Item", 0)  )
    }

    fun addProduct(product: Product) {
        products.add(product)
    }

    fun updateProduct(product: Product) {
        for (p in products) {
            if (p.prodId == product.prodId) {
                products.set(products.indexOf(p), product)
                break
            }
        }
    }

    fun deleteProductById(@PathVariable id: Int) {
        products.removeIf { p -> p.prodId == id }
    }
}