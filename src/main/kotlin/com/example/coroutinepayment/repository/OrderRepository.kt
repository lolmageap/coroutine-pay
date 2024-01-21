package com.example.coroutinepayment.repository

import com.example.coroutinepayment.model.Order
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface OrderRepository: CoroutineCrudRepository<Order, Long> {
}