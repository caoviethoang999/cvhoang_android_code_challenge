package com.example.android_code_challenge.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.android_code_challenge.utils.Converters

@Entity(tableName = "tblArmor")
data class ArmorModelEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int = 0,
    @ColumnInfo(name = "type")
    var type: String = "",
    @ColumnInfo(name = "rank")
    var rank: String = "",
    @ColumnInfo(name = "rarity")
    var rarity: Int = 0,
    @ColumnInfo(name = "defense")
    var defense: Defense? = null,
    @ColumnInfo(name = "resistance")
    var resistance: Resistance? = null,
    @ColumnInfo(name = "attributes")
    var attributes: Attributes? = null,
    @ColumnInfo(name = "name")
    var name: String = "",
    @TypeConverters(Converters::class)
    @ColumnInfo(name = "slots")
    var slots: List<Slots> = listOf(),
    @ColumnInfo(name = "skills")
    var skills: List<Skills> = listOf(),
    @ColumnInfo(name = "armorSet")
    var armorSet: ArmorSets? = null,
    @ColumnInfo(name = "assets")
    var assets: Assets? = null,
    @ColumnInfo(name = "crafting")
    var crafting: Materials? = null,
) {
    @Entity
    data class Defense(
        @ColumnInfo(name = "base")
        var base: Int? = 0,
        @ColumnInfo(name = "max")
        var max: Int? = 0,
        @ColumnInfo(name = "augmented")
        var augmented: Int? = 0,
    )

    @Entity
    data class Resistance(
        @ColumnInfo(name = "fire")
        var fire: Int = 0,
        @ColumnInfo(name = "water")
        var water: Int = 0,
        @ColumnInfo(name = "ice")
        var ice: Int = 0,
        @ColumnInfo(name = "thunder")
        var thunder: Int = 0,
        @ColumnInfo(name = "dragon")
        var dragon: Int = 0,
    )

    @Entity
    data class ArmorSets(
        @ColumnInfo(name = "id")
        var id: Int = 0,
        @ColumnInfo(name = "rank")
        var rank: String = "",
        @ColumnInfo(name = "name")
        var name: String = "",
        @ColumnInfo(name = "pieces")
        var pieces: List<Int>? = null,
        @ColumnInfo(name = "bonus")
        var bonus: Int? = 0,
    )

    @Entity
    data class Assets(
        @ColumnInfo(name = "imageMale")
        var imageMale: String? = "",
        @ColumnInfo(name = "imageFemale")
        var imageFemale: String? = "",
    )

    @Entity
    data class Materials(
        @ColumnInfo(name = "quantity")
        var quantity: Int = 0,
        @ColumnInfo(name = "item")
        var item: Item? = null,
    )

    @Entity
    data class Item(
        @ColumnInfo(name = "id")
        var id: Int = 0,
        @ColumnInfo(name = "rarity")
        var rarity: Int = 0,
        @ColumnInfo(name = "carryLimit")
        var carryLimit: Int = 0,
        @ColumnInfo(name = "value")
        var value: Int = 0,
        @ColumnInfo(name = "name")
        var name: String = "",
        @ColumnInfo(name = "description")
        var description: String = "",
    )

    @Entity
    data class Slots(
        @ColumnInfo(name = "rank")
        var rank: Int = 0,
    ) {
        override fun toString(): String {
            return "$rank"
        }
    }

    @Entity
    data class Skills(
        @ColumnInfo(name = "id")
        var id: Int = 0,
        @ColumnInfo(name = "level")
        var level: Int = 0,
        @ColumnInfo(name = "modifiers")
        var modifiers: Modifiers? = null,
        @ColumnInfo(name = "description")
        var description: String = "",
        @ColumnInfo(name = "skill")
        var skill: Int = 0,
        @ColumnInfo(name = "skillName")
        var skillName: String = ""
    )

    @Entity
    class Attributes

    @Entity
    class Modifiers {
        override fun toString(): String {
            return " "
        }
    }
}