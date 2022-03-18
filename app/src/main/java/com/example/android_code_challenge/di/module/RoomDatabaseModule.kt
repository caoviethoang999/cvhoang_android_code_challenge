package com.example.android_code_challenge.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.android_code_challenge.DatabaseInfo
import com.example.android_code_challenge.database.ArmorDAO
import com.example.android_code_challenge.database.ArmorDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomDatabaseModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

    @DatabaseInfo
    private val mDBName = "test_database.db"

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): ArmorDatabase {
        return Room.databaseBuilder(context, ArmorDatabase::class.java, mDBName)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providePersonDao(db: ArmorDatabase): ArmorDAO {
        return db.armorDAO()
    }
}