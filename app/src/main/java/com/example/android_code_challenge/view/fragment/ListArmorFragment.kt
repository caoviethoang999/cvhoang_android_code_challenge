package com.example.android_code_challenge.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_code_challenge.R
import com.example.android_code_challenge.adapter.ArmorAdapter
import com.example.android_code_challenge.databinding.FragmentListArmorBinding
import com.example.android_code_challenge.repository.ArmorRepository
import com.example.android_code_challenge.utils.clickWithDebounce
import com.example.android_code_challenge.viewmodel.ArmorViewModel
import com.jakewharton.rxbinding4.widget.queryTextChanges
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ListArmorFragment : DaggerFragment() {

    private lateinit var armorAdapter: ArmorAdapter

    @Inject
    lateinit var viewModel: ArmorViewModel

    private lateinit var binding: FragmentListArmorBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO: Rewrite
        val appCompatActivity = activity as AppCompatActivity?
        appCompatActivity?.setSupportActionBar(binding.armorToolbar)
        appCompatActivity?.supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        //TODO: Fix
        viewLifecycleOwner.lifecycleScope.launch {
            delay(3000)
            binding.btnGenerateItem.isEnabled = true
            binding.btnGenerateItem.isClickable = true
        }
        viewModel.status.observe(viewLifecycleOwner) {
            when (it) {
                ArmorRepository.Status.LOADING ->
                    binding.txtLoading.visibility = View.VISIBLE
                ArmorRepository.Status.DONE ->
                    binding.txtLoading.visibility = View.GONE
                else -> {}
            }
        }
        viewModel.message.observe(viewLifecycleOwner) {
            Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
        }
        armorAdapter = ArmorAdapter()
        binding.recyclerViewArmor.adapter = armorAdapter
        binding.recyclerViewArmor.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onResume() {
        super.onResume()
        binding.btnGenerateItem.clickWithDebounce(
            actionIfNotSatisfied = {
                binding.btnGenerateItem.isEnabled = false
                binding.btnGenerateItem.isClickable = false
            },
            action = { gettingDataForLocal() })
    }

    private fun gettingDataForLocal() {
        //TODO: Correct this
        viewModel.getArmor()
        viewModel.armorList.observe(viewLifecycleOwner) {
            armorAdapter.getAll(it)
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

    @SuppressLint("CheckResult")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_armor, menu)
        val menuItem = menu.findItem(R.id.mnSearch)
        val searchView = menuItem.actionView as SearchView
        searchView.queryTextChanges()
            .debounce(1, TimeUnit.SECONDS)
            .filter {
                !TextUtils.isEmpty(it)
            }
            // .debounce { p0 ->
            //     if (p0.isEmpty()) {
            //         Observable.empty()
            //     } else {
            //         Observable.empty<CharSequence>().delay(1, TimeUnit.SECONDS)
            //     }
            // }
            .subscribe {
                Log.d("DebounceTest", it.toString())
                viewModel.searchArmorByName(it.toString())
            }
    }

    // override fun onQueryTextSubmit(p0: String?): Boolean {
    //     return true
    // }
    //
    // @SuppressLint("NotifyDataSetChanged")
    // override fun onQueryTextChange(p0: String?): Boolean {
    //         viewModel.searchArmorByName(p0)
    //         //armorAdapter.notifyDataSetChanged()
    //     return true
    // }


}
