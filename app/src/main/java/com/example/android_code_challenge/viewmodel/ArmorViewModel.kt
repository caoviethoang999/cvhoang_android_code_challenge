package com.example.android_code_challenge.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_code_challenge.model.ArmorModel
import com.example.android_code_challenge.repository.ArmorRepository
import com.example.android_code_challenge.utils.applySchedulers
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class ArmorViewModel @Inject constructor(private val mainRepository: ArmorRepository) : ViewModel() {

    //TODO: Use Backing Property
    var armorList = MutableLiveData<List<ArmorModel>>()
    var status = MutableLiveData<ArmorRepository.Status>()
    var message = MutableLiveData<String>()

    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun getArmor() {
        //TODO: cache in repository
        compositeDisposable.add(mainRepository.fetchArmor()
            .applySchedulers()
            .doOnSubscribe {
                status.postValue(ArmorRepository.Status.LOADING)
            }
            .subscribe({
                insertArmor(it)
                armorList.postValue(it)
                status.postValue(ArmorRepository.Status.DONE)
            }, {
                if (it is UnknownHostException) {
                    getArmorLocal()
                    status.postValue(ArmorRepository.Status.DONE)
                } else if (it is SocketTimeoutException) {
                    message.postValue("Socket Time out. Please try again.")
                }
            })
        )
    }

    private fun getArmorLocal() {
        compositeDisposable.add(mainRepository.getAllArmorLocal()
            .applySchedulers()
            .subscribe({
                armorList.postValue(it)
            }, {
                Log.d(TAG, it.toString())
            })
        )
    }

    fun searchArmorByName(name: String?) {
        compositeDisposable.add(mainRepository.searchArmorByName(name)
            .applySchedulers()
            .subscribe({
                armorList.postValue(it)
            }, {
                Log.d(TAG, it.toString())
            })
        )
    }

    private fun insertArmor(list: List<ArmorModel>) {
        Completable.fromAction {
            mainRepository.insertArmor(list)
        }
            .applySchedulers()
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
