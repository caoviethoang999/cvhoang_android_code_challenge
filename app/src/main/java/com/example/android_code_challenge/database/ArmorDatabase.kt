package com.example.android_code_challenge.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.android_code_challenge.model.ArmorModelEntity
import com.example.android_code_challenge.utils.Converters

@Database(entities = [ArmorModelEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ArmorDatabase : RoomDatabase() {

    abstract fun armorDAO(): ArmorDAO

}