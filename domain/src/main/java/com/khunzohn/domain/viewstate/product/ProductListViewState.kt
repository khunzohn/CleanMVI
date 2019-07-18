package com.khunzohn.domain.viewstate.product

import com.khunzohn.domain.model.Product

data class ProductListViewState(
    val macs: List<Product> = emptyList(),
    val loadingMacs: Boolean = false,
    val loadMacsError: Throwable? = null,
    val iPhones: List<Product> = emptyList(),
    val loadingIPhones: Boolean = false,
    val loadIPhonesError: Throwable? = null,
    val favouriteError: Throwable? = null
)