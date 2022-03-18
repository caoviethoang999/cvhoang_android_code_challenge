package com.example.android_code_challenge.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_code_challenge.R
import com.example.android_code_challenge.adapter.ArmorAdapter
import com.example.android_code_challenge.databinding.FragmentListArmorBinding
import com.example.android_code_challenge.model.ArmorModel
import com.example.android_code_challenge.model.ArmorModelEntity
import com.example.android_code_challenge.viewmodel.ArmorViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ListArmorFragment : DaggerFragment(), SearchView.OnQueryTextListener {
    private var armorAdapter: ArmorAdapter = ArmorAdapter()

    @Inject
    lateinit var viewModel: ArmorViewModel

    private lateinit var binding: FragmentListArmorBinding
    private var list: List<ArmorModel> = ArrayList()
    private var listEntity: List<ArmorModelEntity> = ArrayList()
    var isLocalDataExist = false
    val handler: Handler = Handler()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerViewArmor.layoutManager = LinearLayoutManager(requireContext())
        val appCompatActivity = activity as AppCompatActivity?
        appCompatActivity?.setSupportActionBar(binding.armorToolbar)
        appCompatActivity?.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        // viewModel.getArmor(binding)
        // viewModel.fetchArmorSkill(binding)
        // viewModel.armorList.observe(viewLifecycleOwner){
        //     list= it as ArrayList<ArmorModel>
        //     viewModel.insertArmor(list)
        // }
        // viewModel.armorList.observe(viewLifecycleOwner) {
        //     list= it
        //     armorAdapter.getAll(it)
        //     binding.test.adapter = armorAdapter
        // }
        // if (isLocalDataExist()){
        //     armorAdapter.getAll(listEntity)
        //     binding.test.adapter = armorAdapter
        // }else{
        //     fetchDataRemote()
        // }
        checkingDataLocal()
        handler.postDelayed({
            binding.btnGenerateItem.isEnabled = true
            binding.btnGenerateItem.isClickable = true
        }, 5000)
    }

    override fun onResume() {
        super.onResume()
        binding.btnGenerateItem.clickWithDebounce {
            if (isLocalDataExist) {
                viewModel.armorListLocal.observe(viewLifecycleOwner) {
                    armorAdapter.getAll(it)
                    binding.recyclerViewArmor.adapter = armorAdapter
                }
            } else {
                gettingDataForLocal()
                isLocalDataExist = true
            }
        }
    }

    private fun gettingDataForLocal() {
        viewModel.getArmor(binding)
        viewModel.armorList.observe(viewLifecycleOwner) {
            list = it as java.util.ArrayList
            armorAdapter.getAll(list)
            binding.recyclerViewArmor.adapter = armorAdapter
            viewModel.insertArmor(list)
        }
    }

    private fun checkingDataLocal() {
        viewModel.getArmorLocal()
        viewModel.armorListLocal.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                isLocalDataExist = true
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        binding = FragmentListArmorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_armor, menu)
        val menuItem = menu.findItem(R.id.mnSearch)
        val searchView = menuItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        return true
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onQueryTextChange(p0: String?): Boolean {
        viewModel.searchArmorByName(p0)
        armorAdapter.notifyDataSetChanged()
        return true
    }

    private fun View.clickWithDebounce(debounceTime: Long = 600L, action: () -> Unit) {
        this.setOnClickListener(object : View.OnClickListener {
            private var lastClickTime: Long = 0

            override fun onClick(v: View) {
                if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) {
                    binding.btnGenerateItem.isEnabled = false
                    binding.btnGenerateItem.isClickable = false
                } else {
                    action()
                }

                lastClickTime = SystemClock.elapsedRealtime()
            }
        })
    }
}
