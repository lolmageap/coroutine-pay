package com.example.coroutinepayment.model

import au.com.console.kassava.kotlinEquals
import au.com.console.kassava.kotlinHashCode
import au.com.console.kassava.kotlinToString
import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.annotation.Id
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Table

@Table("product_in_order")
data class ProductInOrder(
    val orderId: Long,

    val productId: Long,

    var price: Long,

    var quantity: Int,

    @Id
    val seq: Long = 0,
): BaseEntity(), Persistable<Long> {

    @Value("null")
    @JsonIgnore
    private var new: Boolean = false

    /**
     * JPA 에서는 아래와 같이 복합키가 사용이 가능하다.
     * 하지만 R2DBC 에서는 아래와 같은 복합키를 사용할 수 없다.
     * class pk(
     *         val orderId: Long,
     *         val productId: Long,
     * )
     *
     * 그래서 별도의 unique key 를 생성해서 관리한다.
     */

    override fun equals(other: Any?) =
        kotlinEquals(
            other,
            arrayOf(
                ProductInOrder::orderId,
                ProductInOrder::productId,
            )
        )


    override fun hashCode() =
        kotlinHashCode(
            arrayOf(
                ProductInOrder::orderId,
                ProductInOrder::productId,
            )
        )

    override fun toString() =
        kotlinToString(
            arrayOf(
                ProductInOrder::orderId,
                ProductInOrder::productId,
                ProductInOrder::price,
                ProductInOrder::quantity,
            ), superToString = { super.toString() }
        )

    override fun getId(): Long = id

    override fun isNew(): Boolean = new
}