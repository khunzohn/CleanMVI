package com.khunzohn.domain.model

sealed class Product {

    data class IPhone(
        val id: String,
        val imageUrl: String,
        val name: String,
        val favourite: Boolean,
        val shortDescription: String,
        val description: String,
        val price: Double,
        val currency: String,
        val inStock: Boolean
    ) : Product()

    data class Mac(
        val id: String,
        val imageUrl: String,
        val name: String,
        val favourite: Boolean,
        val shortDescription: String,
        val description: String,
        val price: Double,
        val currency: String,
        val inStock: Boolean
    ) : Product()
}