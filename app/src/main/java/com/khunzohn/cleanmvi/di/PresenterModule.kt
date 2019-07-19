package com.khunzohn.cleanmvi.di

import com.khunzohn.cleanmvi.feature.product.catalogue.ProductCataloguePresenter
import com.khunzohn.cleanmvi.feature.product.list.ProductListPresenter
import org.koin.dsl.module.module

val presenterModule = module {

    factory { ProductListPresenter(get()) }

    factory { ProductCataloguePresenter(get()) }
}