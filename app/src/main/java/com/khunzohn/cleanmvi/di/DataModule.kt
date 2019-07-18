package com.khunzohn.cleanmvi.di

import com.khunzohn.data.datasource.cache.IPhoneCache
import com.khunzohn.data.datasource.cache.MacCache
import com.khunzohn.data.datasource.remote.GsonProductRemote
import com.khunzohn.data.datasource.remote.ProductRemote
import com.khunzohn.data.repository.ProductRepositoryImpl
import com.khunzohn.data.service.GsonProductService
import com.khunzohn.domain.repository.ProductRepository
import org.koin.dsl.module.module

val dataModule = module {

    single { GsonProductService(get()) }

    single<ProductRemote> { GsonProductRemote(get(), get(), get()) }

    single { IPhoneCache(get(), get()) }

    single { MacCache(get(), get()) }

    single<ProductRepository> { ProductRepositoryImpl(get(), get(), get()) }
}