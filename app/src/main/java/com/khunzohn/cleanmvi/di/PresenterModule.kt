package com.khunzohn.cleanmvi.di

import com.khunzohn.cleanmvi.feature.product.ProductListPresenter
import org.koin.dsl.module.module

val presenterModule = module {
    factory { ProductListPresenter(get()) }
}