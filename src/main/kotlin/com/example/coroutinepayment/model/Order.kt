package com.example.coroutinepayment.model

import au.com.console.kassava.kotlinEquals
import au.com.console.kassava.kotlinHashCode
import au.com.console.kassava.kotlinToString
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("order")
data class Order(
    @Id
    val id: Long = 0,
    val userId: Long,
    var description: String? = null,
    var amount: Long = 0,
    var pgOrderId: String? = null,
    var pgKey: String? = null,
    var pgStatus: PgStatus = PgStatus.CREATE,
    var pgRetryCount: Int = 0,
): BaseEntity() {
    override fun equals(other: Any?) =
        kotlinEquals(
            other,
            arrayOf(
                Order::id
            )
        )


    override fun hashCode() =
        kotlinHashCode(
            arrayOf(
                Order::id
            )
        )

    override fun toString() =
        kotlinToString(
            arrayOf(
                Order::id,
                Order::userId,
                Order::description,
                Order::amount,
                Order::pgOrderId,
                Order::pgKey,
                Order::pgStatus,
                Order::pgRetryCount,
            ), superToString = { super.toString() }
        )
}

