package com.example.android_code_challenge.mapper

import com.example.android_code_challenge.model.ArmorSkillModel
import com.example.android_code_challenge.model.ArmorSkillResponse
import com.example.android_code_challenge.model.JSONArmorSkillResponse

class ArmorSkillMapper {

    fun map(jsonArmorSkillResponse: JSONArmorSkillResponse): ArmorSkillResponse {
        return ArmorSkillResponse(
            skills = jsonArmorSkillResponse.skills?.map { map(it) }
        )
    }

    fun map(skill: JSONArmorSkillResponse.ArmorSkillResponseModel): ArmorSkillModel {
        return ArmorSkillModel(
            id = skill.id,
            level = skill.level,
            modifiers = null,
            description = skill.description,
            skill = skill.skill,
            skillName = skill.skillName
        )
    }
}