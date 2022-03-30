package com.example.android_code_challenge.repository

import com.example.android_code_challenge.model.ArmorModel
import io.reactivex.Single

interface IArmorRepository {
    fun fetchArmor(): Single<List<ArmorModel>>

    fun getAllArmorLocal(): Single<List<ArmorModel>>

    fun searchArmorByName(name: String?): Single<List<ArmorModel>>
}
