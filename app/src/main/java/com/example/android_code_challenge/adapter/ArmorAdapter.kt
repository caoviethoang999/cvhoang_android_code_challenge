package com.example.android_code_challenge.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android_code_challenge.R
import com.example.android_code_challenge.databinding.ItemArmorBinding
import com.example.android_code_challenge.model.ArmorModel

class ArmorAdapter : RecyclerView.Adapter<ArmorAdapter.ItemViewHolder>() {
    private var list: MutableList<ArmorModel> = mutableListOf()

    enum class ArmorType(val imageResource:Int){
        head(R.drawable.ic_head),
        chest(R.drawable.ic_chest),
        gloves(R.drawable.ic_gloves),
        legs(R.drawable.ic_legs),
        waist(R.drawable.ic_waist);

        companion object {
            private val map = values().associateBy(ArmorType::name)

            fun fromStringValue(value: String) = map[value]
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
                    imgIconDeco.visibility = View.INVISIBLE
                    txtSlots.text = ""
                } else {
                    imgIconDeco.visibility = View.VISIBLE
                    imgIconDeco.setImageResource(R.drawable.ic_deco)
                    txtSlots.text = armor.slots.toString()
                }
                txtDefense.text = armor.defense.toString()
                val armorType = ArmorType.fromStringValue(armor.type) ?: return
                imgIcon.setImageResource(armorType.imageResource)
            }
        }
    }
}
