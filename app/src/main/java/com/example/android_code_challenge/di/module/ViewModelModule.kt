package com.example.android_code_challenge.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android_code_challenge.ViewModelKey
import com.example.android_code_challenge.viewmodel.ArmorViewModel
import com.example.android_code_challenge.viewmodel.ArmorViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ArmorViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ArmorViewModel::class)
    abstract fun bindViewModel(mainViewModel: ArmorViewModel): ViewModel

}