package com.example.android_code_challenge.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
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

    override fun onViewAttachedToWindow(holder: ItemViewHolder) {
        Log.d("OnViewAttachedToWindow", holder.toString())
        super.onViewAttachedToWindow(holder)
    }

    override fun onViewDetachedFromWindow(holder: ItemViewHolder) {
        Log.d("OnViewDetachedFromWindow", holder.toString())
        super.onViewDetachedFromWindow(holder)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val armor: ArmorModel = list[position]
        holder.binding.txtId.text = armor.id.toString()
        holder.binding.txtName.text = armor.name
        holder.binding.txtRank.text = "Rank: "+armor.rank.uppercase()
        if (armor.slots.isNullOrEmpty()) {
            holder.binding.imgIconDeco.visibility = View.INVISIBLE
            holder.binding.txtSlots.text = ""
        } else {
            holder.binding.imgIconDeco.visibility = View.VISIBLE
            holder.binding.imgIconDeco.setImageResource(R.drawable.ic_deco)
            holder.binding.txtSlots.text = armor.slots.toString()
        }
        // armor.assets?.imageMale?.let {
        //     val imgUri=armor.assets?.imageMale?.toUri()?.buildUpon()?.scheme("https")?.build()
        //     Glide.with(holder.binding.imgIcon.context)
        //         .load(imgUri)
        //         .apply(
        //             RequestOptions()
        //             .placeholder(R.drawable.loading_animation))
        //         .into(holder.binding.imgIcon)
        // }
        // armor.assets?.imageFemale?.let {
        //     val imgUri=armor.assets?.imageFemale?.toUri()?.buildUpon()?.scheme("https")?.build()
        //     Glide.with(holder.binding.imgIcon.context)
        //         .load(imgUri)
        //         .apply(
        //             RequestOptions()
        //                 .placeholder(R.drawable.loading_animation))
        //         .into(holder.binding.imgIcon)
        // }
        holder.binding.txtDefense.text = armor.defense.toString()
        when {
            armor.type.equals("head", true) -> {
                holder.binding.imgIcon.setImageResource(R.drawable.ic_head)
            }
            armor.type.equals("chest", true) -> {
                holder.binding.imgIcon.setImageResource(R.drawable.ic_chest)
            }
            armor.type.equals("deco", true) -> {
                holder.binding.imgIcon.setImageResource(R.drawable.ic_deco)
            }
            armor.type.equals("gloves", true) -> {
                holder.binding.imgIcon.setImageResource(R.drawable.ic_gloves)
            }
            armor.type.equals("legs", true) -> {
                holder.binding.imgIcon.setImageResource(R.drawable.ic_legs)
            }
            armor.type.equals("shield", true) -> {
                holder.binding.imgIcon.setImageResource(R.drawable.ic_shield)
            }
            armor.type.equals("waist", true) -> {
                holder.binding.imgIcon.setImageResource(R.drawable.ic_waist)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ItemViewHolder(val binding: ItemArmorBinding) :
        RecyclerView.ViewHolder(binding.root)

    init {
        list = ArrayList()
    }
}