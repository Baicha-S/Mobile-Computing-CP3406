package com.example.assignment1.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Appointment::class], version = 1)
@TypeConverters(Converters::class) // Register your com.example.assignment1.data.Converters class here
abstract class AppDatabase : RoomDatabase() {
    abstract fun appointmentDao(): AppointmentDao
}