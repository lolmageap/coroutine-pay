package com.example.coroutinepayment.repository

import com.example.coroutinepayment.model.Product
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface ProductRepository: CoroutineCrudRepository<Product, Long> {
}
