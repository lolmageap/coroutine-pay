package com.example.coroutinepayment

import com.example.coroutinepayment.model.Product
import com.example.coroutinepayment.repository.ProductRepository
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
class CoroutinePaymentApplicationTests(
    @Autowired private val productRepository: ProductRepository,
): StringSpec({

    "context loads" {
        val prevCount = productRepository.count()
        productRepository.save(
            Product(
                name = "name",
                price = 1000
            )
        )
        val nextCount = productRepository.count()

        nextCount shouldBe prevCount + 1
        productRepository.deleteAll()
    }

})