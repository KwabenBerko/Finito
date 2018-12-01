package com.kwabenaberko.finito

import android.app.Activity
import android.app.Application
import com.kwabenaberko.finito.di.DaggerAppComponent
import com.kwabenaberko.finito.di.modules.AppModule
import com.squareup.leakcanary.LeakCanary
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

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);

        DaggerAppComponent.builder()
                .app(this)
                .applicationModule(AppModule(this))
                .build()
                .inject(this)
    }
}