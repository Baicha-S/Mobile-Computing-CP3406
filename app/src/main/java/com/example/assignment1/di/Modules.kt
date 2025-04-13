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
import org.koin.core.scope.get
import org.koin.dsl.module

@RequiresApi(Build.VERSION_CODES.O)
val appModules = module {
    // Room Database
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "appointment-db"
        ).addMigrations(MIGRATION_1_2).build()
    }

    // DAOs
    single { get<AppDatabase>().appointmentDao() }
    single { get<AppDatabase>().petDao() }
    //single { get<AppDatabase>().guidelineDao() }

    // Repositories
    single<AppointmentRepository> { AppointmentRepositoryImpl(get()) }
    single<PetRepository> { PetRepositoryImpl(get()) }
    single<GuidelineRepository> { GuidelineRepositoryImpl() }

    // ViewModels
    viewModel { AppointmentViewModel(get()) }
    viewModel { PetsViewModel(get()) }
    viewModel { PetDetailsViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { AddPetProfileViewModel(get()) }
    viewModel { GuidelineViewModel(get()) }
    viewModel { ExerciseViewModel(get()) }
}
