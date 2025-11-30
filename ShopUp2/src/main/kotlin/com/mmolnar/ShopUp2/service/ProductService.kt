package com.mmolnar.ShopUp2.service

import com.mmolnar.ShopUp2.model.Product
import com.mmolnar.ShopUp2.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.multipart.MultipartFile

@Service
@Transactional
class ProductService {

    @Autowired
    private lateinit var productRepository: ProductRepository

    fun getProductsList(): List<Product> {
        return productRepository.findAll()
    }

    fun getProductById(id: Int): Product {
        return productRepository.findById(id).orElse(Product())
    }

    fun addProduct(product: Product, imageFile: MultipartFile): Product {
        product.imageName = imageFile.originalFilename
        product.imageType = imageFile.contentType.toString()
        product.imageData = imageFile.bytes
        return productRepository.save(product)
    }

    fun updateProduct(id: Int, product: Product, imageFile: MultipartFile): Product {
        product.imageName = imageFile.originalFilename
        product.imageType = imageFile.contentType.toString()
        product.imageData = imageFile.bytes
        return productRepository.save(product)
    }

    fun deleteProductById(@PathVariable id: Int) {
        productRepository.deleteById(id)
    }

    fun searchProducts(keyword: String): List<Product> {
        return productRepository.searchProducts(keyword)
    }


}