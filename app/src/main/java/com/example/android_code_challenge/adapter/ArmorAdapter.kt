package com.example.android_code_challenge.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android_code_challenge.OnItemClickListener
import com.example.android_code_challenge.R
import com.example.android_code_challenge.databinding.ItemArmorBinding
import com.example.android_code_challenge.model.ArmorModel
import com.example.android_code_challenge.utils.ArmorType
import com.example.android_code_challenge.utils.ArmorType.Companion.asEnumOrDefault

class ArmorAdapter(private val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<ArmorAdapter.ItemViewHolder>() {

    private var list: MutableList<ArmorModel> = mutableListOf()


    @SuppressLint("NotifyDataSetChanged")
    fun getAll(list: List<ArmorModel>) {
        this.list.apply {
            clear()
            addAll(list)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemArmorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ItemViewHolder(private val binding: ItemArmorBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(armor: ArmorModel) {
            with(binding) {
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
                armor.type.asEnumOrDefault<ArmorType>()?.let {
                    imgIcon.setImageResource(it.imageResource)
                }
                itemView.setOnClickListener {
                    onItemClickListener.onItemClick(adapterPosition)
                }
            }
        }
    }

}
