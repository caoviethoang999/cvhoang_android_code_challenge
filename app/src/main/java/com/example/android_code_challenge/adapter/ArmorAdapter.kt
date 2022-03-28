package com.example.android_code_challenge.adapter

import android.annotation.SuppressLint
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android_code_challenge.OnItemClickListener
import com.example.android_code_challenge.R
import com.example.android_code_challenge.databinding.HeaderBinding
import com.example.android_code_challenge.databinding.ItemArmorBinding
import com.example.android_code_challenge.model.ArmorModel
import com.example.android_code_challenge.utils.ArmorType
import com.example.android_code_challenge.utils.ArmorType.Companion.asEnumOrDefault

class ArmorAdapter(private val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var list: MutableList<ArmorModel> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun getAll(list: MutableList<ArmorModel>) {
        list.sortBy {
            it.type
        }
        this.list.apply {
            clear()
            addAll(list)
            // sortedWith {
            //         p0, p1 -> p0.type.compareTo(p1.type)
            // }
            // sortBy {
            //     it.type.apply {
            //         this.chars().sorted()
            //     }
            // }
            // sortBy {
            //     it.type
            // }
        }
        // notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> {
                val binding = HeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                TextViewHolder(binding)
            }
            ITEM_VIEW_TYPE_ITEM -> {
                val binding = ItemArmorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ItemViewHolder(binding)
            }
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ItemViewHolder -> {
                if (list.size == 1) {
                    holder.bind(list[position])
                } else {
                    holder.bind(list[position - 1])
                }
            }
            is TextViewHolder -> {
                if (list.size != 0) {
                    holder.bind(list[position])
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return if (list.size == 1) {
            list.size
        } else {
            list.size + 1
        }
    }

    private fun getItem(position: Int): List<Boolean> {
        var lastHeader: String? = ""
        val listTest = ArrayList<Boolean>()
        if (position == list.size) {
            listTest.add(true)
        }
        if (list.size == 1) {
            listTest.add(false)
        }
        for (test in list) {
            val header: String = java.lang.String.valueOf(test.type[0])
            if (!TextUtils.equals(lastHeader, header)) {
                lastHeader = header
                listTest.add(true)
            } else {
                listTest.add(false)
            }
        }
        return listTest
    }

    override fun getItemViewType(position: Int): Int {

        return if (getItem(position)[position]) {
            ITEM_VIEW_TYPE_HEADER
        } else {
            ITEM_VIEW_TYPE_ITEM
        }
    }

    inner class TextViewHolder(private val binding: HeaderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(armor: ArmorModel) {
            with(binding) {
                text.text = armor.type.uppercase()
            }
        }
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
                    if (list.size == 1) {
                        onItemClickListener.onItemClick(adapterPosition)
                    } else {
                        onItemClickListener.onItemClick(adapterPosition - 1)
                    }
                }
            }
        }
    }

    companion object {
        private const val ITEM_VIEW_TYPE_HEADER = 0
        private const val ITEM_VIEW_TYPE_ITEM = 1
    }
}

