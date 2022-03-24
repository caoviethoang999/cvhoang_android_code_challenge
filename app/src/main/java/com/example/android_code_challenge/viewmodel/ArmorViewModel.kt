package com.example.android_code_challenge.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_code_challenge.model.ArmorModel
import com.example.android_code_challenge.repository.ArmorRepository
import com.example.android_code_challenge.utils.applySchedulers
import io.reactivex.disposables.CompositeDisposable
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class ArmorViewModel @Inject constructor(private val mainRepository: ArmorRepository) : ViewModel() {

    private val _armorList = MutableLiveData<List<ArmorModel>>()
    val armorList: LiveData<List<ArmorModel>>
        get() = _armorList

    private val _status = MutableLiveData<ArmorRepository.Status>()
    val status: LiveData<ArmorRepository.Status>
        get() = _status

    private val _message = MutableLiveData<String>()
    val message: LiveData<String>
        get() = _message


    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun getArmor() {
        compositeDisposable.add(
            mainRepository.fetchArmor()
                .applySchedulers()
                .doOnSubscribe {
                    _status.postValue(ArmorRepository.Status.LOADING)
                }
                .doAfterTerminate {
                    _status.postValue(ArmorRepository.Status.DONE)
                }
                .subscribe({
                    // insertArmor(it)
                    _armorList.postValue(it)
                }, {
                    if (it is UnknownHostException) {
                        getArmorLocal()
                    } else if (it is SocketTimeoutException) {
                        _message.postValue("Socket Time out. Please try again.")
                    }
                })
        )
    }

    fun getArmorLocal() {
        compositeDisposable.add(mainRepository.getAllArmorLocal()
            .applySchedulers()
            .subscribe({
                _armorList.postValue(it)
            }, {
                Log.d(TAG, it.toString())
            })
        )
    }

    fun searchArmorByName(name: String?) {
        compositeDisposable.add(
            mainRepository.searchArmorByName(name)
                .applySchedulers()
                .subscribe({
                    _armorList.postValue(it)
                }, {
                    Log.d(TAG, it.toString())
                })
        )
    }

    // private fun insertArmor(list: List<ArmorModel>) {
    //     Completable.fromAction {
    //         mainRepository.insertArmor(list)
    //     }
    //         .applySchedulers()
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
}