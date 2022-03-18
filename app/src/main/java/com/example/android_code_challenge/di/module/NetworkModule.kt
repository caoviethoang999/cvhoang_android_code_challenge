package com.example.android_code_challenge.di.module

import com.example.android_code_challenge.api.ArmorService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    private var retrofitService: ArmorService? = null

    @Singleton
    @Provides
    fun getInstance(): ArmorService {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()
        if (retrofitService == null) {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://mhw-db.com/")
                .addConverterFactory(GsonConverterFactory.create(providesGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
            retrofitService = retrofit.create(ArmorService::class.java)
        }
        return retrofitService!!
    }
    @Provides
    @Singleton
    fun providesGson(): Gson {
        return GsonBuilder().create()
    }
}