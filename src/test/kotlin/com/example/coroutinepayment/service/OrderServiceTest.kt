package com.example.coroutinepayment.service

import com.example.coroutinepayment.model.Product
import com.example.coroutinepayment.repository.OrderRepository
import com.example.coroutinepayment.repository.ProductInOrderRepository
import com.example.coroutinepayment.repository.ProductRepository
import io.kotest.core.spec.style.FunSpec
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.comparables.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
class OrderServiceTest(
    @Autowired private val orderService: OrderService,
    @Autowired private val orderRepository: OrderRepository,
    @Autowired private val productRepository: ProductRepository,
    @Autowired private val productInOrderRepository: ProductInOrderRepository,
) : StringSpec({

    beforeTest {
        orderRepository.deleteAll()
        productRepository.deleteAll()
        productInOrderRepository.deleteAll()
    }

    "create" {
        val product1 = Product(name = "apple", price = 1000)
        val product2 = Product(name = "banana", price = 1200)
        val product3 = Product(name = "mango", price = 700)
        val product4 = Product(name = "orange", price = 2100)

        productRepository.saveAll(
            listOf(product1, product2, product3, product4)
        )

        val createOrder = CreateOrder(
            userId = 1,
            products = listOf(
                ProductQuantity(product1.id, 1),
                ProductQuantity(product2.id, 2),
                ProductQuantity(product3.id, 3),
                ProductQuantity(product4.id, 4),
            )
        )

        val order = orderService.create(createOrder)

        order.amount shouldBe 13900
        order.description shouldNotBe null
        order.pgOrderId shouldNotBe null
        productInOrderRepository.count() shouldBeGreaterThan 4
    }

})
