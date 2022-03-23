package com.example.android_code_challenge.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android_code_challenge.R
import com.example.android_code_challenge.adapter.ArmorAdapter.ArmorType.Companion.asEnumOrDefault
import com.example.android_code_challenge.databinding.ItemArmorBinding
import com.example.android_code_challenge.model.ArmorModel
import java.util.EnumMap

class ArmorAdapter : RecyclerView.Adapter<ArmorAdapter.ItemViewHolder>() {
    private var list: MutableList<ArmorModel> = mutableListOf()


    enum class ArmorType(val imageResource:Int){
        HEAD(R.drawable.ic_head),
        CHEST(R.drawable.ic_chest),
        GLOVES(R.drawable.ic_gloves),
        LEGS(R.drawable.ic_legs),
        WAIST(R.drawable.ic_waist);

        companion object {
            private val map: MutableMap<ArmorType, Int> = EnumMap(com.example.android_code_challenge.adapter.ArmorAdapter.ArmorType::class.java)
            init {
                for (i in ArmorType.values()) {
                    map[i] = i.imageResource
                }
            }
            fun test(armorType: ArmorType): Int? {
                return map[armorType]
            }
            inline fun <reified T : Enum<T>> String.asEnumOrDefault(defaultValue: T? = null): T? =
                enumValues<T>().firstOrNull { it.name.equals(this, ignoreCase = true) } ?: defaultValue
            // fun test2(string: String) = ArmorType.values().firstOrNull{ it.name.equals(this.toString(),true)}
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun getAll(list: List<ArmorModel>) {
        this.list.apply {
            clear()
            addAll(list)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =
            ItemArmorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(list[position])

    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ItemViewHolder(private val binding: ItemArmorBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(armor:ArmorModel){
            with(binding){
                txtId.text = armor.id.toString()
                txtName.text = armor.name
                txtRank.text = armor.rank
                if (armor.slots.isNullOrEmpty()) {
                    imgIconDeco.visibility= View.INVISIBLE
                    txtSlots.text = ""
                } else {
                    imgIconDeco.visibility = View.VISIBLE
                    imgIconDeco.setImageResource(R.drawable.ic_deco)
                    txtSlots.text = armor.slots.toString()
                }
                txtDefense.text = armor.defense.toString()
                armor.type.asEnumOrDefault<ArmorType>()?.let { it -> ArmorType.test(it)?.let { imgIcon.setImageResource(it) } }
            }
        }
    }


}
