package com.khunzohn.cleanmvi.di

import com.khunzohn.domain.interactor.ProductCatalogureInteractor
import com.khunzohn.domain.interactor.ProductListInteractor
import org.koin.dsl.module.module

val interactorModule = module {

    factory { ProductListInteractor(get(), get(), get()) }

    factory { ProductCatalogureInteractor(get(), get(), get()) }
}