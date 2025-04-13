package com.example.assignment1.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Appointment::class, Pet::class, MedicalInfo::class], version = 3)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appointmentDao(): AppointmentDao
    abstract fun petDao(): PetDao
    abstract fun medicalInfoDao(): MedicalInfoDao
}