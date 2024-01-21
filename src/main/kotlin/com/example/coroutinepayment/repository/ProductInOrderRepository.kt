package com.example.coroutinepayment.repository

import com.example.coroutinepayment.model.ProductInOrder
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface ProductInOrderRepository: CoroutineCrudRepository<ProductInOrder, Long> {
}
