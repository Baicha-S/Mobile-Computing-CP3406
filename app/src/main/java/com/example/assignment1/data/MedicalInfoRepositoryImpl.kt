package com.example.assignment1.data

import kotlinx.coroutines.flow.Flow

class MedicalInfoRepositoryImpl(private val medicalInfoDao: MedicalInfoDao) : MedicalInfoRepository {
    override suspend fun insertMedicalInfo(medicalInfo: MedicalInfo) {
        medicalInfoDao.insertMedicalInfo(medicalInfo)
    }

    override fun getMedicalInfoByPetId(petId: Int): Flow<List<MedicalInfo>> {
        return medicalInfoDao.getMedicalInfoByPetId(petId)
    }
}
