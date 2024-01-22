package com.example.coroutinepayment.common

import com.example.coroutinepayment.repository.ProductInOrderRepository
import com.example.coroutinepayment.service.ProductService
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component
import kotlin.reflect.KClass

@Component
class Beans : ApplicationContextAware {

    companion object {
        lateinit var context: ApplicationContext
            private set

        fun <T : Any> getBean(byClass: KClass<T>, vararg arg: Any): T =
            context.getBean(byClass.java, arg)

        val beanProductInOrderRepository: ProductInOrderRepository by lazy {
            getBean(ProductInOrderRepository::class)
        }

        val beanProductService: ProductService by lazy {
            getBean(ProductService::class)
        }

    }

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        context = applicationContext
    }
}