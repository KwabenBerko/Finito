package com.kwabenaberko.finito

import android.app.Activity
import android.app.Application
import com.kwabenaberko.finito.di.DaggerAppComponent
import com.kwabenaberko.finito.di.modules.ApplicationModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class FinitoApp : Application(), HasActivityInjector {

    @Inject
    lateinit var mDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return mDispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
                .app(this)
                .applicationModule(ApplicationModule(this))
                .build()
                .create(this)
    }
}