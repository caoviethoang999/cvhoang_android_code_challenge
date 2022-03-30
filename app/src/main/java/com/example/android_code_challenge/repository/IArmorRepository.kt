package com.example.android_code_challenge.repository

import androidx.lifecycle.MutableLiveData
import com.example.android_code_challenge.model.ArmorModel
import io.reactivex.Single
import io.reactivex.disposables.Disposable

interface IArmorRepository {
    fun fetchArmor(): Single<List<ArmorModel>>

    fun getAllArmorLocal(): Disposable

    fun searchArmorByName(name: String?): Single<List<ArmorModel>>

    val armorList: MutableLiveData<List<ArmorModel>>
}
