package com.example.android_code_challenge.api

import com.example.android_code_challenge.model.JSONArmorResponse
import com.example.android_code_challenge.model.JSONArmorSkillResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url

interface ArmorService {
    //JSon return a array object
    @GET("armor")
    fun getArmor(): Observable<List<JSONArmorResponse>>


    @GET("armor/1")
    fun getArmorSkill(): Observable<JSONArmorSkillResponse>

    // //Json return a object array
    // @GET("armor")
    // fun getArmor(): Observable<JSONArmorResponse>

}