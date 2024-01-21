package com.example.coroutinepayment.model

import au.com.console.kassava.kotlinEquals
import au.com.console.kassava.kotlinHashCode
import au.com.console.kassava.kotlinToString
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("product")
data class Product(
    @Id
    val id: Long = 0,
    var name: String,
    var price: Long,
) {

    override fun equals(other: Any?) =
        kotlinEquals(
            other,
            arrayOf(
                Product::id
            )
        )


    override fun hashCode() =
        kotlinHashCode(
            arrayOf(
                Product::id
            )
        )

    override fun toString() =
        kotlinToString(
            arrayOf(
                Product::id,
                Product::name,
                Product::price,
            ), superToString = { super.toString() }
        )

}