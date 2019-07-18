package com.khunzohn.cleanmvi.di

import com.khunzohn.data.mapper.IPhoneMapper
import com.khunzohn.data.mapper.MacMapper
import org.koin.dsl.module.module

val mapperModule = module {

    factory { IPhoneMapper() }

    factory { MacMapper() }
}