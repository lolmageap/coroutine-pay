package com.example.coroutinepayment.service

import com.example.coroutinepayment.exception.NoOrderException
import com.example.coroutinepayment.exception.NoProductException
import com.example.coroutinepayment.model.Order
import com.example.coroutinepayment.model.PgStatus
import com.example.coroutinepayment.model.ProductInOrder
import com.example.coroutinepayment.repository.OrderRepository
import com.example.coroutinepayment.repository.ProductInOrderRepository
import com.example.coroutinepayment.repository.ProductRepository
import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val productRepository: ProductRepository,
    private val productInOrderRepository: ProductInOrderRepository,
) {

    @Transactional
    suspend fun create(request: CreateOrder): Order {
        val ids = request.products.map { it.productId }.toSet()
        val products = productRepository.findAllById(ids)
            .toList()
            .associateBy { it.id }

        ids.filter { products.containsKey(it) }.let { remains ->
            if (remains.isNotEmpty())
                throw NoProductException("not found id is $remains")
        }

        val amount = request.products.sumOf {
            products[it.productId]!!.price * it.quantity
        }

        val description = request.products.map {
            "${products[it.productId]!!.name} x ${it.quantity}"
        }.joinToString { ", " }

        val order = orderRepository.save(
            Order(
                userId = request.userId,
                description = description,
                amount = amount,
                pgOrderId = "${UUID.randomUUID()}".replace("-", ""),
                pgStatus = PgStatus.CREATE,
            )
        )

        request.products.forEach {
            productInOrderRepository.save(
                ProductInOrder(
                    orderId = order.id,
                    productId = it.productId,
                    price = products[it.productId]!!.price,
                    quantity = it.quantity,
                )
            )
        }

        return order
    }

    suspend fun get(id: Long) =
        orderRepository.findById(id)
            ?: throw NoOrderException()


    suspend fun getAll(userId: Long) =
        orderRepository.findAllByUserIdOrderByCreatedAtDesc(userId)


    suspend fun delete(id: Long) = orderRepository.deleteById(id)

}

data class CreateOrder(
    val userId: Long,
    val products: List<ProductQuantity>,
)

data class ProductQuantity(
    val productId: Long,
    val quantity: Int,
)
