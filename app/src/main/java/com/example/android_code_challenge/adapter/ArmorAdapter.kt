package com.example.android_code_challenge.adapter

import android.annotation.SuppressLint
import android.util.Log
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

    private val _list: MutableList<AdapterItem> = mutableListOf()

    fun getArmorItemAtPosition(position: Int): ArmorModel? {
        return (_list[position] as? ArmorRowItem)?.item
    }

    @SuppressLint("NotifyDataSetChanged")
    fun getAll(addList: MutableList<ArmorModel>) {
        _list.clear()
        val armorGroup = addList.groupBy {
            it.type
        }
        for ((type, itemList) in armorGroup) {
            _list.add(Header(type))
            _list.addAll(itemList.map { ArmorRowItem(it) })
        }
        notifyDataSetChanged()
    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        Log.d("onViewAttachedToWindow",holder.toString())
        super.onViewAttachedToWindow(holder)
    }
    override fun onViewDetachedFromWindow(holder: RecyclerView.ViewHolder) {
        Log.d("onViewDetachedFromWindow",holder.toString())
        super.onViewDetachedFromWindow(holder)
    }
    override fun getItemViewType(position: Int): Int {
        return _list[position].type
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
        val item = _list[position]
        when (holder) {
            is ItemViewHolder -> {
                holder.bind((item as? ArmorRowItem)?.item ?: return)
            }
            is TextViewHolder -> {
                holder.bind((item as? Header)?.itemType ?: return)
            }
        }
    }

    override fun getItemCount(): Int {
        return _list.size
    }

    inner class TextViewHolder(private val binding: HeaderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(header: String) {
            with(binding) {
                text.text = header.uppercase()
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
                    onItemClickListener.onItemClick(adapterPosition)
                }
            }
        }
    }

    private sealed class AdapterItem {
        abstract val type: Int
    }

    private data class ArmorRowItem(val item: ArmorModel) : AdapterItem() {
        override val type = ITEM_VIEW_TYPE_ITEM
    }

    private data class Header(val itemType: String) : AdapterItem() {
        override val type = ITEM_VIEW_TYPE_HEADER
    }

    companion object {
        private const val ITEM_VIEW_TYPE_HEADER = 0
        private const val ITEM_VIEW_TYPE_ITEM = 1
    }
}

