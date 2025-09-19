package com.mmolnar.ShopUp2.controller

import com.mmolnar.ShopUp2.model.Product
import com.mmolnar.ShopUp2.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin
@RequestMapping("/api")
class ProductController {
    @Autowired
    lateinit var productService: ProductService

    @GetMapping("/products")
    fun getProductsList(): ResponseEntity<List<Product>> {
        return ResponseEntity.ok(productService.getProductsList())
        //return productService.getProductsList()
    }

    @GetMapping("/products/{id}")
    fun getProductById(@PathVariable id: Int): ResponseEntity<Product> {
        val product = productService.getProductById(id)

        if (product.id >= 1) {
            return ResponseEntity.ok(product)
        } else {
            return ResponseEntity.notFound().build()
        }

        //return ResponseEntity.ok(productService.getProductById(id))
        //return productService.getProductById(id)
    }

    @PostMapping("/products")
    fun addProduct(@RequestBody product: Product) {
        productService.addProduct(product)
    }

    @PutMapping("/products")
    fun updateProduct(@RequestBody product: Product) {
        productService.updateProduct(product)
    }

    @DeleteMapping("/products/{id}")
    fun deleteProductById(@PathVariable id: Int) {
        productService.deleteProductById(id)
    }
}