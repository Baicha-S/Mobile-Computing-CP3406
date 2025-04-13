package com.example.assignment1.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MedicalInfoDao {
    @Insert
    suspend fun insertMedicalInfo(medicalInfo: MedicalInfo)

    @Query("SELECT * FROM medical_info WHERE petId = :petId")
    fun getMedicalInfoByPetId(petId: Int): Flow<List<MedicalInfo>>
}
