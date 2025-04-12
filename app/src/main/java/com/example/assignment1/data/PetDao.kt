package com.example.assignment1.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PetDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(appointment: Appointment)

    @Query("SELECT * FROM appointments")
    suspend fun getAll(): List<Appointment>

    @Delete
    suspend fun delete(appointment: Appointment)

}
