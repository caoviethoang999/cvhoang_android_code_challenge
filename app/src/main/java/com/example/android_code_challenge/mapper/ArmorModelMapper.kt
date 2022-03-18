package com.example.android_code_challenge.mapper

import com.example.android_code_challenge.model.ArmorModel
import com.example.android_code_challenge.model.ArmorModelEntity
import com.example.android_code_challenge.model.ArmorSkillModel

class ArmorModelMapper {


    fun map(armor: ArmorModel): ArmorModelEntity {
        return ArmorModelEntity(
            id = armor.id,
            type = armor.type,
            rank = armor.rank,
            rarity = armor.rarity,
            defense = armor.defense?.let { map(it) },
            resistance = armor.resistance?.let { map(it) },
            attributes = null,
            name = armor.name,
            slots = armor.slots.map { map(it) },
            skills = armor.skills.map { map(it) },
            armorSet = armor.armorSet?.let { map(it) },
            assets = armor.assets?.let { map(it) },
            crafting = armor.crafting?.let { map(it) }
        )
    }

    private fun map(defense: ArmorModel.Defense): ArmorModelEntity.Defense {
        return ArmorModelEntity.Defense(
            base = defense.base,
            max = defense.max,
            augmented = defense.augmented
        )
    }

    private fun map(resistance: ArmorModel.Resistance): ArmorModelEntity.Resistance {
        return ArmorModelEntity.Resistance(
            fire = resistance.fire,
            water = resistance.water,
            ice = resistance.ice,
            thunder = resistance.thunder,
            dragon = resistance.dragon
        )
    }

    private fun map(slots: ArmorModel.Slots): ArmorModelEntity.Slots {
        return ArmorModelEntity.Slots(
            rank = slots.rank
        )
    }

    private fun map(skill: ArmorSkillModel): ArmorModelEntity.Skills {
        return ArmorModelEntity.Skills(
            id = skill.id,
            level = skill.level,
            modifiers = null,
            description = skill.description,
            skill = skill.skill,
            skillName = skill.skillName
        )
    }


    private fun map(JSONArmorSets: ArmorModel.ArmorSets): ArmorModelEntity.ArmorSets {
        return ArmorModelEntity.ArmorSets(
            id = JSONArmorSets.id,
            rank = JSONArmorSets.rank,
            name = JSONArmorSets.name,
            pieces = JSONArmorSets.pieces,
            bonus = JSONArmorSets.bonus
        )
    }

    private fun map(assets: ArmorModel.Assets): ArmorModelEntity.Assets {
        return ArmorModelEntity.Assets(
            imageMale = assets.imageMale,
            imageFemale = assets.imageFemale
        )
    }

    private fun map(materials: ArmorModel.Materials): ArmorModelEntity.Materials {
        return ArmorModelEntity.Materials(
            quantity = materials.quantity,
            item = materials.item?.let { map(it) }
        )
    }
    private fun map(items: ArmorModel.Item): ArmorModelEntity.Item {
        return ArmorModelEntity.Item(
            id = items.id,
            rarity = items.rarity,
            carryLimit = items.carryLimit,
            value = items.value,
            name = items.name,
            description = items.description
        )
    }

    fun map(armor: ArmorModelEntity): ArmorModel {
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
            skills = armor.skills.map {
                        map(it)
            },
            armorSet = armor.armorSet?.let { map(it) },
            assets = armor.assets?.let { map(it) },
            crafting = armor.crafting?.let { map(it) }
        )
    }

    fun map(skill: ArmorModelEntity.Skills): ArmorSkillModel {
        return ArmorSkillModel(
            id = skill.id,
            level = skill.level,
            modifiers = null,
            description = skill.description,
            skill = skill.skill,
            skillName = skill.skillName
        )
    }

    private fun map(defense: ArmorModelEntity.Defense): ArmorModel.Defense {
        return ArmorModel.Defense(
            base = defense.base!!,
            max = defense.max!!,
            augmented = defense.augmented!!
        )
    }

    private fun map(resistance: ArmorModelEntity.Resistance): ArmorModel.Resistance {
        return ArmorModel.Resistance(
            fire = resistance.fire,
            water = resistance.water,
            ice = resistance.ice,
            thunder = resistance.thunder,
            dragon = resistance.dragon
        )
    }

    private fun map(slots: ArmorModelEntity.Slots): ArmorModel.Slots {
        return ArmorModel.Slots(
            rank = slots.rank
        )
    }


    private fun map(JSONArmorSets: ArmorModelEntity.ArmorSets): ArmorModel.ArmorSets {
        return ArmorModel.ArmorSets(
            id = JSONArmorSets.id,
            rank = JSONArmorSets.rank,
            name = JSONArmorSets.name,
            pieces = JSONArmorSets.pieces,
            bonus = JSONArmorSets.bonus
        )
    }

    private fun map(assets: ArmorModelEntity.Assets): ArmorModel.Assets {
        return ArmorModel.Assets(
            imageMale = assets.imageMale,
            imageFemale = assets.imageFemale
        )
    }

    private fun map(materials: ArmorModelEntity.Materials): ArmorModel.Materials {
        return ArmorModel.Materials(
            quantity = materials.quantity,
            item = materials.item?.let { map(it) }
        )
    }
    private fun map(items: ArmorModelEntity.Item): ArmorModel.Item {
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