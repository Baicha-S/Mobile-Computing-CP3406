package com.example.assignment1.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment1.data.MedicalInfo
import com.example.assignment1.data.MedicalInfoRepository
import kotlinx.coroutines.launch

class AddMedicalInfoViewModel(private val medicalInfoRepository: MedicalInfoRepository) :
    ViewModel() {

    fun addMedicalInfo(medicalInfo: MedicalInfo) {
        viewModelScope.launch {
            medicalInfoRepository.insertMedicalInfo(medicalInfo)
        }
    }
}

