package com.example.coroutinepayment.service

import com.example.coroutinepayment.model.Product
import com.example.coroutinepayment.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository,
) {

    suspend fun get(id: Long): Product? {
        return productRepository.findById(id)
    }

}