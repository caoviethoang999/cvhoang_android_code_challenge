package com.example.android_code_challenge.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.Objects

data class JSONArmorResponse(
        @SerializedName("id")
        val id: Int = 0,
        @SerializedName("type")
        val type: String = "",
        @SerializedName("rank")
        val rank: String = "",
        @SerializedName("rarity")
        val rarity: Int = 0,
        @SerializedName("defense")
        val defense: Defense? = null,
        @SerializedName("resistance")
        val resistance: Resistance? = null,
        @SerializedName("attributes")
        val attributes: Attributes? = null,
        @SerializedName("name")
        val name: String = "",
        @SerializedName("slots")
        val slots: List<Slots> = listOf(),
        @SerializedName("skills")
        val skills: List<JSONArmorSkillResponse.ArmorSkillResponseModel> = listOf(),
        @SerializedName("armorSet")
        val armorSet: ArmorSets? = null,
        @SerializedName("assets")
        val assets: Assets? = null,
        @SerializedName("crafting")
        val crafting: Materials? = null,
    ) {
        data class Defense(
            @SerializedName("base")
            val base: Int = 0,
            @SerializedName("max")
            val max: Int = 0,
            @SerializedName("augmented")
            val augmented: Int = 0,
        )
        data class Resistance(
            @SerializedName("fire")
            val fire: Int = 0,
            @SerializedName("water")
            val water: Int = 0,
            @SerializedName("ice")
            val ice: Int = 0,
            @SerializedName("thunder")
            val thunder: Int = 0,
            @SerializedName("dragon")
            val dragon: Int = 0,
        )

        data class ArmorSets(
            @SerializedName("id")
            val id: Int = 0,
            @SerializedName("rank")
            val rank: String = "",
            @SerializedName("name")
            val name: String = "",
            @SerializedName("pieces")
            val pieces: List<Int>? = null,
            @SerializedName("bonus")
            val bonus: Int? = 0,
        )

        data class Assets(
            @SerializedName("imageMale")
            val imageMale: String? = "",
            @SerializedName("imageFemale")
            val imageFemale: String? = "",
        )

        data class Materials(
            @SerializedName("quantity")
            val quantity: Int = 0,
            @SerializedName("item")
            val item: Item? = null,
        )

        data class Item(
            @SerializedName("id")
            val id: Int = 0,
            @SerializedName("rarity")
            val rarity: Int = 0,
            @SerializedName("carryLimit")
            val carryLimit: Int = 0,
            @SerializedName("value")
            val value: Int = 0,
            @SerializedName("name")
            val name: String = "",
            @SerializedName("description")
            val description: String = "",
        )

        data class Slots(
            @SerializedName("rank")
            val rank: Int = 0,
        )

        class Attributes
    }