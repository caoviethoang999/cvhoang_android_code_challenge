package com.example.android_code_challenge.repository

import android.annotation.SuppressLint
import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android_code_challenge.api.ArmorService
import com.example.android_code_challenge.database.ArmorDAO
import com.example.android_code_challenge.mapper.ArmorMapper
import com.example.android_code_challenge.mapper.ArmorModelMapper
import com.example.android_code_challenge.model.ArmorModel
import com.example.android_code_challenge.utils.applySchedulers
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import java.net.UnknownHostException
import javax.inject.Inject

class ArmorRepository @Inject constructor(
    private val armorService: ArmorService,
    private val armorDAO: ArmorDAO,
    private val mapperArmor: ArmorMapper,
    private val mapperArmorModel: ArmorModelMapper,
) : IArmorRepository {

    enum class Status { LOADING, ERROR, DONE }

    private val _status = MutableLiveData<Status>()

    val status: LiveData<Status>
        get() = _status

    private val _armorList = MutableLiveData<List<ArmorModel>>()

    override val armorList: MutableLiveData<List<ArmorModel>>
        get() = _armorList

    override fun fetchArmor(): Single<List<ArmorModel>> {
        return armorService.getArmor()
            .map { it ->
                it.map { json ->
                    mapperArmor.map(json)
                }
            }
            .doOnSuccess {
                insertArmor(it)
            }
            .doOnError {
                if (it is UnknownHostException) {
                    getAllArmorLocal()
                }
            }
    }

    @SuppressLint("CheckResult")
    override fun getAllArmorLocal(): Disposable {
        return armorDAO.getAllArmor()
            .map { it ->
                it.map {
                    mapperArmorModel.map(it)
                }
            }
            .applySchedulers()
            .subscribe({
                _armorList.postValue(it)
            }, {
                Log.d(ContentValues.TAG, it.toString())
            })
    }

    @SuppressLint("CheckResult")
    override fun searchArmorByName(name: String?): Single<List<ArmorModel>> {
        return armorDAO.searchArmorByName(name)
            .map { it ->
                it.map {
                    mapperArmorModel.map(it)
                }
            }
    }

    private fun insertArmor(list: List<ArmorModel>) {
        val listMapping = list.map {
            mapperArmorModel.map(it)
        }
        armorDAO.insertAllArmor(listMapping)
    }
}
