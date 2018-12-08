package com.kwabenaberko.finito.di.modules

import com.kwabenaberko.finito.ContextDispatchers
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ContextDispatchersModule {
    @Singleton
    @Provides
    fun provideDispatcherProvider(): ContextDispatchers{
        return ContextDispatchers()
    }
}