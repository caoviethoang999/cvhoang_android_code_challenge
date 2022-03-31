package com.example.android_code_challenge.view.fragment

import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.os.bundleOf
import com.bumptech.glide.Glide
import com.example.android_code_challenge.databinding.FragmentArmorDetailBinding
import com.example.android_code_challenge.model.ArmorModel
import com.example.android_code_challenge.utils.ArmorType
import com.example.android_code_challenge.utils.ArmorType.Companion.asEnumOrDefault
import dagger.android.support.DaggerFragment

class ArmorDetailFragment : DaggerFragment() {

    companion object {
        private const val EXTRA_KEY_MODEL = "EXTRA_KEY_MODEL"

        fun newInstance(model: ArmorModel) = ArmorDetailFragment().apply {
            arguments = bundleOf(EXTRA_KEY_MODEL to model)
        }
    }

    private lateinit var binding: FragmentArmorDetailBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(ContentValues.TAG, "OnViewCreated:Called")
        (activity as AppCompatActivity).apply {
            setSupportActionBar(binding.armorToolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        val bundle = this.arguments
        val test: ArmorModel? = bundle?.getParcelable("EXTRA_KEY_MODEL")
        binding.txtDefense.text = test?.defense?.printString()
        binding.txtResistance.text = test?.resistance?.toString()
        binding.txtName.text = test?.name
        binding.txtSlots.text = test?.slots.toString()
        test?.type?.asEnumOrDefault<ArmorType>()?.let {
            binding.imgIconType.setImageResource(it.imageResource)
        }
        test?.assets?.imageFemale?.let {
            val imgUri = test.assets?.imageFemale?.toUri()?.buildUpon()?.scheme("https")?.build()
            binding.imgIconFemale.let { it1 ->
                Glide.with(it1.context)
                        .load(imgUri)
                        .into(binding.imgIconFemale)
            }
        }
        test?.assets?.imageMale?.let {
            val imgUri = test.assets?.imageMale?.toUri()?.buildUpon()?.scheme("https")?.build()
            binding.imgIconMale.let { it1 ->
                Glide.with(it1.context)
                        .load(imgUri)
                        .into(binding.imgIconMale)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(ContentValues.TAG, "OnAttach:Called")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(ContentValues.TAG, "OnCreate:Called")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        Log.d(ContentValues.TAG, "OnCreateView:Called")
        binding = FragmentArmorDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                activity?.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
