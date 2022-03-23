package com.example.android_code_challenge.repository

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android_code_challenge.api.ArmorService
import com.example.android_code_challenge.database.ArmorDAO
import com.example.android_code_challenge.mapper.ArmorMapper
import com.example.android_code_challenge.mapper.ArmorModelMapper
import com.example.android_code_challenge.mapper.ArmorSkillMapper
import com.example.android_code_challenge.model.ArmorModel
import com.example.android_code_challenge.model.ArmorSkillModel
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.Observer
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException
import javax.inject.Inject

class ArmorRepository @Inject constructor(
    private val armorService: ArmorService,
    private val armorDAO: ArmorDAO,
    private val mapperArmor: ArmorMapper,
    private val mapper: ArmorSkillMapper,
    private val mapperArmorModel: ArmorModelMapper,
) {

    enum class Status { LOADING, ERROR, DONE }

    private val _status = MutableLiveData<Status>()

    val status: LiveData<Status>
        get() = _status

    fun fetchArmor(): Single<List<ArmorModel>> {
        return armorService.getArmor()
            .map { it ->
                it.map { json ->
                    mapperArmor.map(json)
                }
            }
    }

    @SuppressLint("CheckResult")
    fun getAllArmorLocal(): Single<List<ArmorModel>> {
        return armorDAO.getAllArmor()
            .map { it ->
                it.map {
                    mapperArmorModel.map(it)
                }
            }
    }

    @SuppressLint("CheckResult")
    fun searchArmorByName(name: String?): Single<List<ArmorModel>> {
        return armorDAO.searchArmorByName(name)
            .map { it ->
                it.map {
                    mapperArmorModel.map(it)
                }
            }
    }

    fun insertArmor(list: List<ArmorModel>)
    {
        val listMapping=list.map {
                mapperArmorModel.map(it)
            }
        armorDAO.insertAllArmor(listMapping)
    }
}
