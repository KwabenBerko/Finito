package com.kwabenaberko.finito.di.modules

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule (private val application: Application) {
    @Singleton
    @Provides
    fun provideApplication(): Application{
        return application
    }
}