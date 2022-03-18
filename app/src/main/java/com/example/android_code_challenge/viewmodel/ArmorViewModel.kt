package com.example.android_code_challenge.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android_code_challenge.databinding.FragmentListArmorBinding
import com.example.android_code_challenge.model.ArmorModel
import com.example.android_code_challenge.model.ArmorModelEntity
import com.example.android_code_challenge.model.ArmorSkillModel
import com.example.android_code_challenge.repository.ArmorRepository
import javax.inject.Inject

class ArmorViewModel @Inject constructor(private val mainRepository: ArmorRepository) : ViewModel() {

    var armorList = MutableLiveData<List<ArmorModel>>()
    var armorListSkill = MutableLiveData<List<ArmorSkillModel>>()
    var armorListLocal = MutableLiveData<List<ArmorModel>>()


    // var armorList = MutableLiveData<MutableList<JSONArmorResponse>>()

    // fun getArmor(binding: FragmentListArmorBinding) {
    //     armorList= mainRepository.fetchArmor(binding)
    // }

    fun getArmor(binding: FragmentListArmorBinding) {
        armorList= mainRepository.fetchArmor(binding)
    }

    fun fetchArmorSkill(binding: FragmentListArmorBinding) {
        armorListSkill= mainRepository.fetchArmorSkill(binding)
    }

    fun getArmorLocal() {
        armorListLocal= mainRepository.getAllArmorLocal()
    }

    fun searchArmorByName(name:String?) {
        armorListLocal= mainRepository.searchArmorByName(name)
    }

    fun insertArmor(list: List<ArmorModel>){
        mainRepository.insertArmor(list)
    }
}