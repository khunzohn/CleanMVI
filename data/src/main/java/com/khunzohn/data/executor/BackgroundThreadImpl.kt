package com.khunzohn.data.executor

import com.khunzohn.domain.executor.BackgroundThread
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class BackgroundThreadImpl constructor(
    private val jobsExecutor: JobsExecutor
) : BackgroundThread {

    override fun getScheduler(): Scheduler = Schedulers.from(jobsExecutor)
}
