package com.example.android_code_challenge.utils

import androidx.room.TypeConverter
import com.example.android_code_challenge.model.ArmorModelEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {


    @TypeConverter
    fun modifiersToString(armor9: ArmorModelEntity.Modifiers?): String = Gson().toJson(armor9)

    @TypeConverter
    fun stringToModifiers(string: String?): ArmorModelEntity.Modifiers? {
        return if (string != null) {
            Gson().fromJson(string, ArmorModelEntity.Modifiers::class.java)
        } else {
            null
        }
    }

    @TypeConverter
    fun skillsToString(armor8: List<ArmorModelEntity.Skills>?): String = Gson().toJson(armor8)

    @TypeConverter
    fun stringToSkills(string: String?): List<ArmorModelEntity.Skills>? {
        return if (string != null) {
            Gson().fromJson<List<ArmorModelEntity.Skills>?>(
                string,
                object : TypeToken<List<ArmorModelEntity.Skills>?>() {}.type
            )
        } else {
            null
        }
    }

    @TypeConverter
    fun defenseToString(armor7: ArmorModelEntity.Defense?): String = Gson().toJson(armor7)

    @TypeConverter
    fun stringToDefense(string: String?): ArmorModelEntity.Defense? {
        return if (string != null) {
            Gson().fromJson(string, ArmorModelEntity.Defense::class.java)
        } else {
            null
        }
    }

    @TypeConverter
    fun resistanceToString(armor6: ArmorModelEntity.Resistance?): String = Gson().toJson(armor6)

    @TypeConverter
    fun stringToResistance(string: String?): ArmorModelEntity.Resistance? {
        return if (string != null) {
            Gson().fromJson(string, ArmorModelEntity.Resistance::class.java)
        } else {
            null
        }
    }

    @TypeConverter
    fun attributesToString(armor5: ArmorModelEntity.Attributes?): String = Gson().toJson(armor5)

    @TypeConverter
    fun stringToAttributes(string: String?): ArmorModelEntity.Attributes? {
        return if (string != null) {
            Gson().fromJson(string, ArmorModelEntity.Attributes::class.java)
        } else {
            null
        }
    }

    @TypeConverter
    fun armorSetsToString(armor4: ArmorModelEntity.ArmorSets?): String = Gson().toJson(armor4)

    @TypeConverter
    fun stringToArmorSets(string: String?): ArmorModelEntity.ArmorSets? {
        return if (string != null) {
            Gson().fromJson(string, ArmorModelEntity.ArmorSets::class.java)
        } else {
            null
        }
    }

    @TypeConverter
    fun assetsToString(armor3: ArmorModelEntity.Assets?): String = Gson().toJson(armor3)

    @TypeConverter
    fun stringToAssets(string: String?): ArmorModelEntity.Assets? {
        return if (string != null) {
            Gson().fromJson(string, ArmorModelEntity.Assets::class.java)
        } else {
            null
        }
    }

    @TypeConverter
    fun materialsToString(armor2: ArmorModelEntity.Materials?): String = Gson().toJson(armor2)

    @TypeConverter
    fun stringToMaterials(string: String?): ArmorModelEntity.Materials? {
        return if (string != null) {
            Gson().fromJson(string, ArmorModelEntity.Materials::class.java)
        } else {
            null
        }
    }

    @TypeConverter
    fun slotsToString(armor1: List<ArmorModelEntity.Slots>?): String = Gson().toJson(armor1)

    @TypeConverter
    fun stringToSlots(string: String?): List<ArmorModelEntity.Slots>? {
        return if (string != null) {
            Gson().fromJson<List<ArmorModelEntity.Slots>?>(
                string,
                object : TypeToken<List<ArmorModelEntity.Slots>?>() {}.type
            )
        } else {
            null
        }
    }
}