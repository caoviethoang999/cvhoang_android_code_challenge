package com.example.android_code_challenge.di.module

import com.example.android_code_challenge.view.fragment.ListArmorFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeListArmorFragment(): ListArmorFragment
}