package com.mmolnar.ShopUp2.controller

import com.mmolnar.ShopUp2.model.Product
import com.mmolnar.ShopUp2.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@CrossOrigin
@RequestMapping("/api")
class ProductController {
    @Autowired
    lateinit var productService: ProductService

    @GetMapping("/products")
    fun getProductsList(): ResponseEntity<List<Product>> {
        return ResponseEntity.ok(productService.getProductsList())
    }

    @GetMapping("/product/{id}")
    fun getProductById(@PathVariable id: Int): ResponseEntity<Product> {
        val product = productService.getProductById(id)

        if (product.id >= 1) {
            return ResponseEntity.ok(product)
        } else {
            return ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/product/{id}/image")
    fun getImageByProductId(@PathVariable id: Int): ResponseEntity<ByteArray> {
        val product = productService.getProductById(id)
        val imageFile = product.imageData

        return ResponseEntity.ok().contentType(MediaType.valueOf(product.imageType)).body(imageFile)
    }

    @PostMapping("/product")
    fun addProduct(@RequestPart product: Product, @RequestPart imageFile: MultipartFile): ResponseEntity<Product> {
        try {
            val newProduct = productService.addProduct(product, imageFile)
            return ResponseEntity.ok(newProduct)
        } catch(e: Exception) {
            return ResponseEntity.internalServerError().build()
        }

    }

    @PutMapping("/product/{id}")
    fun updateProduct(@PathVariable id: Int, @RequestPart product: Product, @RequestPart imageFile: MultipartFile): ResponseEntity<String> {
        val updatedProduct = productService.updateProduct(id, product, imageFile)

        if (product.id >= 1) {
            return ResponseEntity.ok("Product updated")
        } else {
            return ResponseEntity.badRequest().build()
        }
    }

    @DeleteMapping("/product/{id}")
    fun deleteProductById(@PathVariable id: Int): ResponseEntity<String> {
        val product = productService.getProductById(id)
        if (product.id >= 1) {
            productService.deleteProductById(id)
            return ResponseEntity.ok("Product deleted")
        } else {
            return ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/products/search")
    fun searchProducts(@RequestParam keyword: String): ResponseEntity<List<Product>> {
        val products = productService.searchProducts(keyword)
        return ResponseEntity.ok(products)
    }
}