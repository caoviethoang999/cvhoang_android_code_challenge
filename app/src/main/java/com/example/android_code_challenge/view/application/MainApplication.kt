package com.example.android_code_challenge.view.application

import com.example.android_code_challenge.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class MainApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {

        return DaggerApplicationComponent.builder().application(this).build().apply { inject(this@MainApplication) }
    }
}