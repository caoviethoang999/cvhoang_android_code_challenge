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

    private var armorList = MutableLiveData<List<ArmorModel>>()

    private var armorListLocal = MutableLiveData<List<ArmorModel>>()

    private var armorListSkill = MutableLiveData<List<ArmorSkillModel>>()


    // fun fetchArmor(): MutableLiveData<List<ArmorModel>> {
    //     armorService.getArmor()
    //         .subscribeOn(Schedulers.io())
    //         .observeOn(AndroidSchedulers.mainThread())
    //         .map { it ->
    //             it.map { json ->
    //                 mapperArmor.map(json)
    //             }
    //         }
    //         .subscribe(object : SingleObserver<List<ArmorModel>> {
    //             override fun onSubscribe(d: Disposable) {
    //                 _status.postValue(Status.LOADING)
    //             }
    //
    //             override fun onError(e: Throwable) {
    //                 if (e is UnknownHostException) {
    //                     // val errorBody = (e as IOException).message
    //                     getAllArmorLocal()
    //                     _status.postValue(Status.DONE)
    //                 }
    //             }
    //
    //             override fun onSuccess(t: List<ArmorModel>) {
    //                 insertArmor(t)
    //                 armorList.postValue(t)
    //                 _status.postValue(Status.DONE)
    //             }
    //         })
    //
    //     return armorList
    // }
    fun fetchArmorSkill(): MutableLiveData<List<ArmorSkillModel>> {
        armorService.getArmorSkill()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                mapper.map(it)
            }
            .map {
                val list = it.skills!!
                list
            }
            .subscribe(object : Observer<List<ArmorSkillModel>> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: List<ArmorSkillModel>) {
                    armorListSkill.postValue(t)
                }

                override fun onError(e: Throwable) {
                    Log.d("Log Error", e.toString())
                }

                override fun onComplete() {
                }
            })
        return armorListSkill
    }

    // @SuppressLint("CheckResult")
    // fun getAllArmorLocal(): MutableLiveData<List<ArmorModel>> {
    //     armorDAO.getAllArmor()
    //         .subscribeOn(Schedulers.io())
    //         .map {
    //             it.map {
    //                 mapperArmorModel.map(it)
    //             }
    //         }
    //         .observeOn(AndroidSchedulers.mainThread())
    //         .subscribe(object : SingleObserver<List<ArmorModel>> {
    //             override fun onSubscribe(d: Disposable) {
    //             }
    //
    //             override fun onError(e: Throwable) {
    //                 Log.d("Log Error", e.toString())
    //             }
    //
    //             override fun onSuccess(t: List<ArmorModel>) {
    //                 armorList.postValue(t)
    //             }
    //         })
    //     return armorList
    // }
    //
    // @SuppressLint("CheckResult")
    // fun searchArmorByName(name: String?): MutableLiveData<List<ArmorModel>> {
    //     armorDAO.searchArmorByName(name)
    //         .subscribeOn(Schedulers.io())
    //         .map {
    //             it.map {
    //                 mapperArmorModel.map(it)
    //             }
    //         }
    //         .observeOn(AndroidSchedulers.mainThread())
    //         .subscribe (object : SingleObserver<List<ArmorModel>> {
    //             override fun onSubscribe(d: Disposable) {
    //             }
    //
    //             override fun onError(e: Throwable) {
    //                 Log.d("Log Error", e.toString())
    //             }
    //
    //             override fun onSuccess(t: List<ArmorModel>) {
    //                 armorList.postValue(t)
    //             }
    //         })
    //
    //     return armorList
    // }
    //
    //
    // fun insertArmor(list: List<ArmorModel>) {
    //     val listTest = list.map {
    //         mapperArmorModel.map(it)
    //     }
    //     Completable.fromAction {
    //         armorDAO.insertAllArmor(listTest)
    //     }.subscribeOn(Schedulers.io())
    //         .observeOn(AndroidSchedulers.mainThread())
    //         .subscribe(object : CompletableObserver {
    //             override fun onSubscribe(d: Disposable) {
    //                 Log.d(TAG, "onSubscribe: Called")
    //             }
    //
    //             override fun onComplete() {
    //                 Log.d(TAG, "onComplete: Called")
    //             }
    //
    //             override fun onError(e: Throwable) {
    //                 Log.d("Log Error", e.toString())
    //             }
    //         })
    // }


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
            .map {
                it.map { it ->
                    mapperArmorModel.map(it)
                }
            }
    }

    @SuppressLint("CheckResult")
    fun searchArmorByName(name: String?): Single<List<ArmorModel>> {
        return armorDAO.searchArmorByName(name)
            .map {
                it.map {it ->
                    mapperArmorModel.map(it)
                }
            }
    }

    fun insertArmor(list: List<ArmorModel>)
    {
            list.map {
                mapperArmorModel.map(it)
            }
    }
}
