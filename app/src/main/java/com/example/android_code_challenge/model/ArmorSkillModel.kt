package com.example.android_code_challenge.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class ArmorSkillResponse(
    val skills: List<ArmorSkillModel>? = null
)

@Parcelize
class ArmorSkillModel(
    var id: Int = 0,
    var level: Int = 0,
    var modifiers: Modifiers? = null,
    var description: String = "",
    var skill: Int = 0,
    var skillName: String = ""
) : Parcelable {

    override fun toString(): String {
        return "$skillName - $description"
    }
    @Parcelize
    class Modifiers : Parcelable {
        override fun toString(): String {
            return " "
        }
    }
}