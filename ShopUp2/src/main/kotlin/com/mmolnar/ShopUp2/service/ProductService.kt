package com.mmolnar.ShopUp2.service

import com.mmolnar.ShopUp2.model.Product
import com.mmolnar.ShopUp2.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable

@Service
class ProductService {

    @Autowired
    private lateinit var productRepository: ProductRepository

    /*val products = mutableListOf<Product>(
        Product(101, "Galaxy S25", 1000),
        Product(102, "Bathtub", 800),
        Product(103, "Picture Frame", 20)
    )*/

    fun getProductsList(): List<Product> {
        //return products
        return productRepository.findAll()
    }

    fun getProductById(id: Int): Product {
        /*return products.stream()
            .filter { p -> p.prodId == id }
            .findFirst()
            .orElse(Product(100, "No Item", 0)  )*/
        return productRepository.findById(id).orElse(Product())
    }

    fun addProduct(product: Product) {
        //products.add(product)
        productRepository.save(product)
    }

    fun updateProduct(product: Product) {
        /*for (p in products) {
            if (p.prodId == product.prodId) {
                products.set(products.indexOf(p), product)
                break
            }
        }*/
        productRepository.save(product)
    }

    fun deleteProductById(@PathVariable id: Int) {
        //products.removeIf { p -> p.prodId == id }
        productRepository.deleteById(id)
    }
}