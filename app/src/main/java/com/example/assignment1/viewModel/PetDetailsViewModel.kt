package com.example.assignment1.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment1.data.MedicalInfo
import com.example.assignment1.data.Pet
import com.example.assignment1.data.PetDao
import com.example.assignment1.data.MedicalInfoRepository
import kotlinx.coroutines.launch

class PetDetailsViewModel(private val petDao: PetDao, private val medicalInfoRepository: MedicalInfoRepository) : ViewModel() {
    private val _petDetails = MutableLiveData<Pet>()
    val petDetails: LiveData<Pet> = _petDetails

    private val _medicalHistory = MutableLiveData<List<MedicalInfo>>()
    val medicalHistory: LiveData<List<MedicalInfo>> = _medicalHistory

    fun loadPetDetails(petId: Int) {
        viewModelScope.launch {
            _petDetails.value = petDao.getPetById(petId)
            medicalInfoRepository.getMedicalInfoByPetId(petId).collect{
                _medicalHistory.value = it
            }
        }
    }

    fun addMedicalInfo(medicalInfo: MedicalInfo) {
        viewModelScope.launch {
            medicalInfoRepository.insertMedicalInfo(medicalInfo)
        }
    }
}