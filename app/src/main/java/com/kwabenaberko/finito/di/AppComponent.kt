package com.kwabenaberko.finito.di

import com.kwabenaberko.finito.FinitoApp
import com.kwabenaberko.finito.di.modules.ActivitiesBindingModule
import com.kwabenaberko.finito.di.modules.ApplicationModule
import com.kwabenaberko.finito.di.modules.RepositoryModule
import com.kwabenaberko.finito.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivitiesBindingModule::class,
    ApplicationModule::class,
    RepositoryModule::class,
    ViewModelModule::class
])
interface AppComponent : AndroidInjector<FinitoApp>{
    @Component.Builder
    interface Builder{
        @BindsInstance
        fun app(app: FinitoApp): Builder
        fun applicationModule(applicationModule: ApplicationModule): Builder
        fun build(): AppComponent
    }

    override fun inject(app: FinitoApp)
}