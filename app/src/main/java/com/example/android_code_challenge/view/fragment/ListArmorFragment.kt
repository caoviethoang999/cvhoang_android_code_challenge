package com.example.android_code_challenge.view.fragment

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_code_challenge.OnItemClickListener
import com.example.android_code_challenge.R
import com.example.android_code_challenge.adapter.ArmorAdapter
import com.example.android_code_challenge.databinding.FragmentListArmorBinding
import com.example.android_code_challenge.model.ArmorModel
import com.example.android_code_challenge.repository.ArmorRepository
import com.example.android_code_challenge.utils.clickWithDebounce
import com.example.android_code_challenge.viewmodel.ArmorViewModel
import com.jakewharton.rxbinding4.widget.queryTextChanges
import dagger.android.support.DaggerFragment
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ListArmorFragment : DaggerFragment(), OnItemClickListener {

    private lateinit var armorAdapter: ArmorAdapter

    @Inject
    lateinit var viewModel: ArmorViewModel
    var count = 0
    private lateinit var binding: FragmentListArmorBinding
    private var list: List<ArmorModel> = listOf()
    private var mAlreadyLoaded = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d(TAG, "OnViewCreated:Called")
        (activity as AppCompatActivity).apply {
            setSupportActionBar(binding.armorToolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        armorAdapter = ArmorAdapter(this)
        binding.recyclerViewArmor.adapter = armorAdapter
        binding.recyclerViewArmor.layoutManager = LinearLayoutManager(requireContext())

        if (savedInstanceState == null && !mAlreadyLoaded) {
            mAlreadyLoaded = true
            viewModel.getArmor()
        } else {
            viewModel.getArmorLocal()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "OnAttach:Called")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "OnStart:Called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "OnStop:Called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "OnPause:Called")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "OnDestroyView:Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "OnDestroy:Called")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "OnDetach:Called")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "OnCreate:Called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "OnResume:Called")
        binding.btnGenerateItem.clickWithDebounce(
            actionIfNotSatisfied = {
            },
            action = {
                viewModel.getArmor()
            })
    }

    private fun gettingDataForLocal() {
        //getting subscribe too many time
        //request too many time on server lead it to socket time out
        viewModel.armorList.observe(viewLifecycleOwner) {
            count++
            Log.d(TAG, "TEST:$count")
            list = it
            armorAdapter.getAll(list)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        gettingDataForLocal()
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
            if (it.isNotEmpty()) {
                binding.btnGenerateItem.isClickable = true
                binding.btnGenerateItem.isEnabled = true
            }
        }
        setHasOptionsMenu(true)
        Log.d(TAG, "OnCreateView:Called")
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
            .subscribe {
                Log.d("DebounceTest", it.toString())
                viewModel.searchArmorByName(it.toString())
            }
    }

    override fun onItemClick(position: Int) {
        val fragment = ArmorDetailFragment()
        val bundle = Bundle()
        bundle.putParcelable("test", list[position])
        fragment.arguments = bundle
        parentFragmentManager.beginTransaction().addToBackStack(null).replace(R.id.fragment_container, fragment)
            .commit()
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
