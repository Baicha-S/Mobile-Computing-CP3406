package com.example.assignment1.data

import kotlinx.coroutines.flow.Flow

interface MedicalInfoRepository {
    suspend fun insertMedicalInfo(medicalInfo: MedicalInfo)
    fun getMedicalInfoByPetId(petId: Int): Flow<List<MedicalInfo>>
}
