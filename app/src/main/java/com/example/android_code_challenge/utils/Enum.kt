package com.example.android_code_challenge.utils

import com.example.android_code_challenge.R

enum class ArmorType(val imageResource: Int) {
    HEAD(R.drawable.ic_head),
    CHEST(R.drawable.ic_chest),
    GLOVES(R.drawable.ic_gloves),
    LEGS(R.drawable.ic_legs),
    WAIST(R.drawable.ic_waist);

    companion object {
        inline fun <reified T : Enum<T>> String.asEnumOrDefault() =
            enumValues<T>().firstOrNull { it.name.equals(this, ignoreCase = true) }

        private val map = values().associateBy(ArmorType::name)
        fun fromString(string: String) = map[string]
    }
}