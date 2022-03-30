package com.example.android_code_challenge.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android_code_challenge.ViewModelKey
import com.example.android_code_challenge.api.ArmorService
import com.example.android_code_challenge.database.ArmorDAO
import com.example.android_code_challenge.mapper.ArmorMapper
import com.example.android_code_challenge.mapper.ArmorModelMapper
import com.example.android_code_challenge.repository.ArmorRepository
import com.example.android_code_challenge.repository.IArmorRepository
import com.example.android_code_challenge.viewmodel.ArmorViewModel
import com.example.android_code_challenge.viewmodel.ArmorViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ArmorViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ArmorViewModel::class)
    abstract fun bindViewModel(mainViewModel: ArmorViewModel): ViewModel
}