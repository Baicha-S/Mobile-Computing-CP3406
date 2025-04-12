package com.example.assignment1.di

import com.example.assignment1.data.AppDatabase
import com.example.assignment1.data.AppointmentRepositoryImpl
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Room
import com.example.assignment1.data.*
import com.example.assignment1.viewModel.*
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@RequiresApi(Build.VERSION_CODES.O)
val appModules = module {
    // Room Database
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "appointment-db"
        ).build()
    }

    // DAO
    single { get<AppDatabase>().appointmentDao() }

    // Repository
    single<AppointmentRepository> { AppointmentRepositoryImpl(get()) }

    // ViewModel
    viewModel { AppointmentViewModel(get()) }

    // Existing dependencies
    single<PetRepository> { PetRepositoryImpl() }
    viewModel { PetsViewModel(get()) }
    viewModel { PetDetailsViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    single<GuidelineRepository> { GuidelineRepositoryImpl() }
    viewModel { GuidelineViewModel(get()) }
    viewModel { ExerciseViewModel() }
}
