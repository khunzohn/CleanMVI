package com.khunzohn.domain.interactor

import com.khunzohn.domain.executor.PostExecutionThread
import com.khunzohn.domain.executor.BackgroundThread

open class BaseInteractor(
    val mainThread: PostExecutionThread,
    val backgroundThread: BackgroundThread
)
