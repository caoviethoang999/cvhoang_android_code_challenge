package com.example.android_code_challenge.di.module

import com.example.android_code_challenge.mapper.ArmorMapper
import com.example.android_code_challenge.mapper.ArmorModelMapper
import com.example.android_code_challenge.mapper.ArmorSkillMapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MapperModule {

    @Singleton
    @Provides
    fun mapperArmor() = ArmorMapper()

    @Singleton
    @Provides
    fun mapperArmorSkill() = ArmorSkillMapper()

    @Singleton
    @Provides
    fun mapperArmorModel() = ArmorModelMapper()

}