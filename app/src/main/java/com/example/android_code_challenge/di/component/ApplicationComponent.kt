package com.example.android_code_challenge.di.component

import android.app.Application
import com.example.android_code_challenge.view.application.MainApplication
import com.example.android_code_challenge.di.module.ActivityBuildersModule
import com.example.android_code_challenge.di.module.FragmentBuildersModule
import com.example.android_code_challenge.di.module.MapperModule
import com.example.android_code_challenge.di.module.NetworkModule
import com.example.android_code_challenge.di.module.RoomDatabaseModule
import com.example.android_code_challenge.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ActivityBuildersModule::class,
        FragmentBuildersModule::class,
        NetworkModule::class,
        ViewModelModule::class,
        MapperModule::class,
        RoomDatabaseModule::class,
        AndroidSupportInjectionModule::class]
)
interface ApplicationComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(application: MainApplication)
}