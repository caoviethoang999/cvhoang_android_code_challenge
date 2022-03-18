package com.example.android_code_challenge.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android_code_challenge.R
import com.example.android_code_challenge.databinding.ItemArmorBinding
import com.example.android_code_challenge.model.ArmorModel

class ArmorAdapter : RecyclerView.Adapter<ArmorAdapter.ItemViewHolder>() {
    private var list: List<ArmorModel>
    fun getAll(list: List<ArmorModel>) {
        this.list = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =
            ItemArmorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        var armor: ArmorModel = list[position]
        holder.binding.txtId.text = armor.id.toString()
        holder.binding.txtName.text = armor.name
        holder.binding.txtRank.text = armor.rank
        if (armor.slots.isNullOrEmpty()) {
            holder.binding.txtSlots.text = ""
        } else {
            holder.binding.txtSlots.text = armor.slots.toString()
        }
        holder.binding.txtDefense.text = armor.defense.toString()
        if (armor.type.equals("head", true)) {
            holder.binding.imgIcon.setImageResource(R.drawable.ic_head)
        } else if (armor.type.equals("chest", true)) {
            holder.binding.imgIcon.setImageResource(R.drawable.ic_chest)
        } else if (armor.type.equals("deco", true)) {
            holder.binding.imgIcon.setImageResource(R.drawable.ic_deco)
        } else if (armor.type.equals("gloves", true)) {
            holder.binding.imgIcon.setImageResource(R.drawable.ic_gloves)
        } else if (armor.type.equals("legs", true)) {
            holder.binding.imgIcon.setImageResource(R.drawable.ic_legs)
        } else if (armor.type.equals("shield", true)) {
            holder.binding.imgIcon.setImageResource(R.drawable.ic_shield)
        } else if (armor.type.equals("waist", true)) {
            holder.binding.imgIcon.setImageResource(R.drawable.ic_waist)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ItemViewHolder(val binding: ItemArmorBinding) :
        RecyclerView.ViewHolder(binding.root)

    init {
        list = ArrayList<ArmorModel>()
    }
}