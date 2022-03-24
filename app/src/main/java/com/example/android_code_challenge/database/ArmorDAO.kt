package com.example.android_code_challenge.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android_code_challenge.model.ArmorModelEntity
import io.reactivex.Observable
import io.reactivex.Single

@Dao

interface ArmorDAO {

    @Query("SELECT * FROM tblArmor")
    fun getAllArmor(): Single<List<ArmorModelEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertAllArmor(armorModelList: List<ArmorModelEntity>)

    @Query("SELECT * FROM tblArmor WHERE name LIKE '%' || :name || '%'")
    fun searchArmorByName(name: String?): Single<List<ArmorModelEntity>>

}