package com.example.android_code_challenge.mapper

import com.example.android_code_challenge.model.ArmorModel
import com.example.android_code_challenge.model.JSONArmorResponse

class ArmorMapper {

    private val armorSkillMapper = ArmorSkillMapper()

    fun map(armor: JSONArmorResponse): ArmorModel {
        return ArmorModel(
            id = armor.id,
            type = armor.type,
            rank = armor.rank,
            rarity = armor.rarity,
            defense = armor.defense?.let { map(it) },
            resistance = armor.resistance?.let { map(it) },
            attributes = null,
            name = armor.name,
            slots = armor.slots.map { map(it) },
            skills = armor.skills.map { armorSkillMapper.map(it) },
            armorSet = armor.armorSet?.let { map(it) },
            assets = armor.assets?.let { map(it) },
            crafting = armor.crafting?.let { map(it) }
        )
    }

    private fun map(defense: JSONArmorResponse.Defense): ArmorModel.Defense {
        return ArmorModel.Defense(
            base = defense.base,
            max = defense.max,
            augmented = defense.augmented
        )
    }

    private fun map(resistance: JSONArmorResponse.Resistance): ArmorModel.Resistance {
        return ArmorModel.Resistance(
            fire = resistance.fire,
            water = resistance.water,
            ice = resistance.ice,
            thunder = resistance.thunder,
            dragon = resistance.dragon
        )
    }

    private fun map(slots: JSONArmorResponse.Slots): ArmorModel.Slots {
        return ArmorModel.Slots(
            rank = slots.rank
        )
    }

    private fun map(JSONArmorSets: JSONArmorResponse.ArmorSets): ArmorModel.ArmorSets {
        return ArmorModel.ArmorSets(
            id = JSONArmorSets.id,
            rank = JSONArmorSets.rank,
            name = JSONArmorSets.name,
            pieces = JSONArmorSets.pieces,
            bonus = JSONArmorSets.bonus
        )
    }

    private fun map(assets: JSONArmorResponse.Assets): ArmorModel.Assets {
        return ArmorModel.Assets(
            imageMale = assets.imageMale,
            imageFemale = assets.imageFemale
        )
    }

    private fun map(materials: JSONArmorResponse.Materials): ArmorModel.Materials {
        return ArmorModel.Materials(
            quantity = materials.quantity,
            item = materials.item?.let { map(it) }
        )
    }

    private fun map(items: JSONArmorResponse.Item): ArmorModel.Item {
        return ArmorModel.Item(
            id = items.id,
            rarity = items.rarity,
            carryLimit = items.carryLimit,
            value = items.value,
            name = items.name,
            description = items.description
        )
    }
}