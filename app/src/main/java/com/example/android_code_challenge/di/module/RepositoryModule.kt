package com.example.android_code_challenge.di.module

import com.example.android_code_challenge.api.ArmorService
import com.example.android_code_challenge.database.ArmorDAO
import com.example.android_code_challenge.mapper.ArmorMapper
import com.example.android_code_challenge.mapper.ArmorModelMapper
import com.example.android_code_challenge.repository.ArmorRepository
import com.example.android_code_challenge.repository.IArmorRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(
        armorService: ArmorService,
        armorDAO: ArmorDAO,
        armorMapper: ArmorMapper,
        armorModelMapper: ArmorModelMapper
    ): IArmorRepository {
        return ArmorRepository(armorService, armorDAO, armorMapper, armorModelMapper)
    }
}