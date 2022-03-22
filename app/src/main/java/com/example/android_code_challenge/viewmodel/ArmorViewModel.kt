package com.example.android_code_challenge.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_code_challenge.model.ArmorModel
import com.example.android_code_challenge.model.ArmorSkillModel
import com.example.android_code_challenge.repository.ArmorRepository
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.net.UnknownHostException
import javax.inject.Inject

class ArmorViewModel @Inject constructor(private val mainRepository: ArmorRepository) : ViewModel() {

    var armorList = MutableLiveData<List<ArmorModel>>()
    var armorListSkill = MutableLiveData<List<ArmorSkillModel>>()
    var armorListLocal = MutableLiveData<List<ArmorModel>>()
    var status = MutableLiveData<ArmorRepository.Status>()
    // var status = mainRepository.status


    // var armorList = MutableLiveData<MutableList<JSONArmorResponse>>()

    // fun getArmor(binding: FragmentListArmorBinding) {
    //     armorList= mainRepository.fetchArmor(binding)
    // }

    fun getArmor() {
        mainRepository.fetchArmor()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<ArmorModel>> {
                override fun onSubscribe(d: Disposable) {
                    status.postValue(ArmorRepository.Status.LOADING)
                }

                override fun onError(e: Throwable) {
                    if (e is UnknownHostException) {
                        // val errorBody = (e as IOException).message
                        getArmorLocal()
                        status.postValue(ArmorRepository.Status.DONE)
                    }
                }

                override fun onSuccess(t: List<ArmorModel>) {
                    insertArmor(t)
                    armorList.postValue(t)
                    status.postValue(ArmorRepository.Status.DONE)
                }
            })
    }

    fun fetchArmorSkill() {
        armorListSkill = mainRepository.fetchArmorSkill()
    }

    fun getArmorLocal() {
        mainRepository.getAllArmorLocal()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<ArmorModel>> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                }

                override fun onSuccess(t: List<ArmorModel>) {
                    armorList.postValue(t)
                }
            })
    }

    fun searchArmorByName(name: String?) {
        mainRepository.searchArmorByName(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<ArmorModel>> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                }

                override fun onSuccess(t: List<ArmorModel>) {
                    armorList.postValue(t)
                }
            })
    }

    fun insertArmor(list: List<ArmorModel>) {
        Completable.fromAction {
            mainRepository.insertArmor(list)
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