package com.example.android_code_challenge.utils

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Single<T>.applySchedulers(): Single<T> = this
    .subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())

fun Completable.applySchedulers(): Completable = this
    .subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())

