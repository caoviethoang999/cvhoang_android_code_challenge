package com.example.android_code_challenge.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArmorModel(
    var id: Int = 0,
    var type: String = "",
    var rank: String = "",
    var rarity: Int = 0,
    var defense: Defense? = null,
    var resistance: Resistance? = null,
    var attributes: Attributes? = null,
    var name: String = "",
    var slots: List<Slots> = listOf(),
    var skills: List<ArmorSkillModel> = listOf(),
    var armorSet: ArmorSets? = null,
    var assets: Assets? = null,
    var crafting: Materials? = null,
) : Parcelable {
    @Parcelize
    data class Defense(
        var base: Int = 0,
        var max: Int = 0,
        var augmented: Int = 0,
    ) : Parcelable {
        override fun toString(): String {
            return "base: $base - max: $max - augmented: $augmented"
        }
        fun printString():String{
            return " base: $base\n max: $max\n augmented: $augmented\n"
        }
    }

    @Parcelize
    data class Resistance(
        var fire: Int = 0,
        var water: Int = 0,
        var ice: Int = 0,
        var thunder: Int = 0,
        var dragon: Int = 0,
    ) : Parcelable {
        override fun toString(): String {
            return " fire: $fire\n" +
                " water: $water\n" +
                " ice: $ice\n" +
                " thunder: $thunder\n" +
                " dragon: $dragon\n"
        }
    }


    @Parcelize
    data class ArmorSets(
        var id: Int = 0,
        var rank: String = "",
        var name: String = "",
        var pieces: List<Int>? = null,
        var bonus: Int? = 0,
    ) : Parcelable

    @Parcelize
    data class Assets(
        var imageMale: String? = "",
        var imageFemale: String? = "",
    ) : Parcelable

    @Parcelize
    data class Materials(
        var quantity: Int = 0,
        var item: Item? = null,
    ) : Parcelable

    @Parcelize
    data class Item(
        var id: Int = 0,
        var rarity: Int = 0,
        var carryLimit: Int = 0,
        var value: Int = 0,
        var name: String = "",
        var description: String = "",
    ) : Parcelable

    @Parcelize
    data class Slots(
        var rank: Int = 0,
    ) : Parcelable {
        override fun toString(): String {
            return "$rank"
        }
    }

    @Parcelize
    class Attributes : Parcelable
}