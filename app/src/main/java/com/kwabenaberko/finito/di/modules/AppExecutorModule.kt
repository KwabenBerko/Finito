package com.kwabenaberko.finito.di.modules

import com.kwabenaberko.finito.AppExecutors
import dagger.Module
import dagger.Provides
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
class AppExecutorModule {
    @Singleton
    @Provides
    fun provideAppExecutors(): AppExecutors{
        return AppExecutors(
                Executors.newSingleThreadExecutor(),
                AppExecutors.MainThreadExecutor()
        )
    }
}