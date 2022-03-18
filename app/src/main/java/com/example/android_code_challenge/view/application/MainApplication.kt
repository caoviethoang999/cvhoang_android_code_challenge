package com.example.android_code_challenge.view.application

import com.example.android_code_challenge.di.component.ApplicationComponent
import com.example.android_code_challenge.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class MainApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {

        val component: ApplicationComponent =
            DaggerApplicationComponent.builder().application(this).build()
        component.inject(this)

        return component
    }
}