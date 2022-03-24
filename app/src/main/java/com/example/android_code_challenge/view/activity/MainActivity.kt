package com.example.android_code_challenge.view.activity

import android.os.Bundle
import com.example.android_code_challenge.R
import com.example.android_code_challenge.databinding.ActivityMainBinding
import com.example.android_code_challenge.view.fragment.ListArmorFragment
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val fragment = ListArmorFragment()
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, fragment)
            .commit()
    }

}