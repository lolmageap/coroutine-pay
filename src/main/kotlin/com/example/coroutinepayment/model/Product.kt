package com.example.coroutinepayment.model

import au.com.console.kassava.kotlinEquals
import au.com.console.kassava.kotlinHashCode
import au.com.console.kassava.kotlinToString
import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.annotation.Id
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Table

@Table("product")
data class Product(
    @Id
    val id: Long = 0,
    var name: String,
    var price: Long,
): BaseEntity(), Persistable<Long> {

    @Value("null")
    @JsonIgnore
    private var new: Boolean = false

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

    override fun getId(): Long = id

    override fun isNew(): Boolean = new
}