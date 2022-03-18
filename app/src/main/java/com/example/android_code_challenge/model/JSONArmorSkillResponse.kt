package com.example.android_code_challenge.model

import com.google.gson.annotations.SerializedName

data class JSONArmorSkillResponse(val skills: MutableList<ArmorSkillResponseModel>? = null){

data class ArmorSkillResponseModel(
@SerializedName("id")
val id:Int = 0,
@SerializedName("level")
val level:Int = 0,
@SerializedName("modifiers")
val modifiers: Modifiers? = null,
@SerializedName("description")
val description:String = "",
@SerializedName("skill")
val skill:Int = 0,
@SerializedName("skillName")
val skillName:String = ""
) {
    class Modifiers
}
}