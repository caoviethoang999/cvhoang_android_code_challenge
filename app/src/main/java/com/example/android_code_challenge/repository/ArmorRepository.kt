package com.example.android_code_challenge.repository

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.android_code_challenge.api.ArmorService
import com.example.android_code_challenge.database.ArmorDAO
import com.example.android_code_challenge.databinding.FragmentListArmorBinding
import com.example.android_code_challenge.mapper.ArmorMapper
import com.example.android_code_challenge.mapper.ArmorModelMapper
import com.example.android_code_challenge.mapper.ArmorSkillMapper
import com.example.android_code_challenge.model.ArmorModel
import com.example.android_code_challenge.model.ArmorSkillModel
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ArmorRepository @Inject constructor(
    private val armorService: ArmorService,
    private val armorDAO: ArmorDAO,
    private val mapperArmor: ArmorMapper,
    private val mapper: ArmorSkillMapper,
    private val mapperArmorModel: ArmorModelMapper,
) {

    private var armorList = MutableLiveData<List<ArmorModel>>()

    private var armorListLocal = MutableLiveData<List<ArmorModel>>()

    private var armorListSkill = MutableLiveData<List<ArmorSkillModel>>()

    // private var armorList = MutableLiveData<MutableList<JSONArmorResponse>>()

    // fun fetchArmor(): MutableLiveData<MutableList<ArmorSkill>> {
    //     armorService.getArmor()
    //         .subscribeOn(Schedulers.io())
    //         .map { armorResponse ->
    //             var list:MutableList<ArmorSkill> = armorResponse.skills.toMutableList()
    //
    //             list
    //         }
    //         .observeOn(AndroidSchedulers.mainThread())
    //         .subscribe(object : Observer<MutableList<ArmorSkill>> {
    //             override fun onSubscribe(d: Disposable) {
    //             }
    //
    //             override fun onNext(t: MutableList<ArmorSkill>) {
    //                 armorList.postValue(t)
    //             }
    //
    //             override fun onError(e: Throwable) {
    //                 Log.d("Log Error",e.toString())
    //             }
    //
    //             override fun onComplete() {
    //             }
    //         })
    //     return armorList
    // }

    // fun fetchArmor(binding: FragmentListArmorBinding): MutableLiveData<MutableList<JSONArmorResponse>> {
    //     armorService.getArmor()
    //         .subscribeOn(Schedulers.io())
    //         .observeOn(AndroidSchedulers.mainThread())
    //         .subscribe(object : Observer<MutableList<JSONArmorResponse>> {
    //             override fun onSubscribe(d: Disposable) {
    //                 binding.txtLoading.visibility=android.view.View.VISIBLE
    //             }
    //
    //             override fun onNext(t: MutableList<JSONArmorResponse>) {
    //                 armorList.postValue(t)
    //             }
    //
    //             override fun onError(e: Throwable) {
    //                 Log.d("Log Error",e.toString())
    //             }
    //
    //             override fun onComplete() {
    //                 binding.txtLoading.visibility=android.view.View.INVISIBLE
    //             }
    //         })
    //     return armorList
    // }

    fun fetchArmor(binding: FragmentListArmorBinding): MutableLiveData<List<ArmorModel>> {
        armorService.getArmor()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it ->
                it.map { json ->
                    mapperArmor.map(json)
                }
            }
            .subscribe(object : Observer<List<ArmorModel>> {
                override fun onSubscribe(d: Disposable) {
                    binding.txtLoading.visibility = android.view.View.VISIBLE
                }

                override fun onNext(t: List<ArmorModel>) {
                    armorList.postValue(t)
                }

                override fun onError(e: Throwable) {
                    Log.d("Log Error", e.toString())
                }

                override fun onComplete() {
                    binding.txtLoading.visibility = android.view.View.INVISIBLE
                }
            })
        return armorList
    }

    fun fetchArmorSkill(binding: FragmentListArmorBinding): MutableLiveData<List<ArmorSkillModel>> {
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
                    binding.txtLoading.visibility = android.view.View.VISIBLE
                }

                override fun onNext(t: List<ArmorSkillModel>) {
                    armorListSkill.postValue(t)
                }

                override fun onError(e: Throwable) {
                    Log.d("Log Error", e.toString())
                }

                override fun onComplete() {
                    binding.txtLoading.visibility = android.view.View.INVISIBLE
                }
            })
        return armorListSkill
    }

    @SuppressLint("CheckResult")
    fun getAllArmorLocal(): MutableLiveData<List<ArmorModel>> {
        armorDAO.getAllArmor()
            .subscribeOn(Schedulers.io())
            .map {
                it.map {
                    mapperArmorModel.map(it)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<ArmorModel>> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: List<ArmorModel>) {
                    armorListLocal.postValue(t)
                }

                override fun onError(e: Throwable) {
                    Log.d("Log Error", e.toString())
                }

                override fun onComplete() {
                    Log.d("Log", "onComplete")
                }
            })
        return armorListLocal
    }

    @SuppressLint("CheckResult")
    fun searchArmorByName(name: String?): MutableLiveData<List<ArmorModel>> {
        armorDAO.searchArmorByName(name)
            .subscribeOn(Schedulers.io())
            .map {
                it.map {
                    mapperArmorModel.map(it)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { t ->
                armorListLocal.postValue(t)
            }
        return armorListLocal
    }

    fun insertArmor(list: List<ArmorModel>) {
        val listTest = list.map {
            mapperArmorModel.map(it)
        }
        Completable.fromAction {
            armorDAO.insertAllArmor(listTest)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver {
                override fun onSubscribe(d: Disposable) {
                    Log.d(TAG, "onSubscribe: Called")
                }

                override fun onComplete() {
                    Log.d(TAG, "onComplete: Called")
                }

                override fun onError(e: Throwable) {
                    Log.d("Log Error", e.toString())
                }
            })
    }
}
