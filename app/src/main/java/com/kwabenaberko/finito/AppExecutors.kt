package com.kwabenaberko.finito

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor

class AppExecutors(
        private val diskIO: Executor,
        private val mainThread: Executor
) {

    fun diskIO(): Executor{
        return diskIO
    }

    fun mainThread(): Executor{
        return mainThread
    }

    class MainThreadExecutor: Executor{
        private val mHandler = Handler(Looper.getMainLooper())
        override fun execute(runnable: Runnable) {
            mHandler.post(runnable)
        }

    }

}