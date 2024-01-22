package com.example.coroutinepayment.controller

import com.example.coroutinepayment.common.Beans.Companion.beanProductInOrderRepository
import com.example.coroutinepayment.common.Beans.Companion.beanProductService
import com.example.coroutinepayment.model.Order
import com.example.coroutinepayment.model.PgStatus
import com.example.coroutinepayment.service.CreateOrder
import com.example.coroutinepayment.service.OrderService
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/order")
class OrderController(
    private val orderService: OrderService,
) {

    @GetMapping("/{order-id}")
    suspend fun get(@PathVariable("order-id") id: Long) =
        orderService.get(id)
            .let { OrderResponse.of(it) }

    @GetMapping("/user/{user-id}")
    suspend fun getAll(@PathVariable("user-id") userId: Long) =
        orderService.getAll(userId)
            .map { OrderResponse.of(it) }

    @PostMapping
    suspend fun create(@RequestBody createOrder: CreateOrder) =
        orderService.create(createOrder)
            .let { OrderResponse.of(it) }

    @DeleteMapping("/{order-id}")
    suspend fun delete(@PathVariable("order-id") id: Long) =
        orderService.delete(id)

}

data class OrderResponse(
    val id: Long,
    val userId: Long,
    val description: String? = null,
    val amount: Long,
    val pgOrderId: String? = null,
    val pgKey: String? = null,
    val pgStatus: PgStatus,
    val pgRetryCount: Int,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val products: List<ProductQuantityResponse>,
) {
    companion object {
        suspend fun of(order: Order) =
            OrderResponse(
                id = order.id,
                userId = order.userId,
                description = order.description,
                amount = order.amount,
                pgOrderId = order.pgOrderId,
                pgKey = order.pgKey,
                pgStatus = order.pgStatus,
                pgRetryCount = order.pgRetryCount,
                createdAt = order.createdAt!!,
                updatedAt = order.updatedAt!!,
                products = beanProductInOrderRepository.findAllByOrderId(order.id).map { productInOrder ->
                    ProductQuantityResponse(
                        id = productInOrder.productId,
                        name = beanProductService.get(productInOrder.productId)?.name ?: "unknown",
                        price = productInOrder.price,
                        quantity = productInOrder.quantity,
                    )
                },
            )
    }
}

data class ProductQuantityResponse(
    val id: Long,
    val name: String,
    val price: Long,
    val quantity: Int,
)