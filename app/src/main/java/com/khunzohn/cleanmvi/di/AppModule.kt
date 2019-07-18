package com.khunzohn.cleanmvi.di

import com.khunzohn.cleanmvi.executor.UiThread
import com.khunzohn.data.executor.BackgroundThreadImpl
import com.khunzohn.data.executor.JobsExecutor
import com.khunzohn.domain.executor.BackgroundThread
import com.khunzohn.domain.executor.PostExecutionThread
import org.koin.dsl.module.module

val appModule = module {

    single { UiThread() as PostExecutionThread }

    single<BackgroundThread> { BackgroundThreadImpl(JobsExecutor()) }
}