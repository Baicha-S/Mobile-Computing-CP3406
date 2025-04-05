package com.example.assignment1.di

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.assignment1.data.GuidelineRepository
import com.example.assignment1.data.GuidelineRepositoryImpl
import com.example.assignment1.data.PetRepository
import com.example.assignment1.data.PetRepositoryImpl
import com.example.assignment1.viewModel.ExerciseViewModel
import com.example.assignment1.viewModel.GuidelineViewModel
import com.example.assignment1.viewModel.AppointmentViewModel
import com.example.assignment1.viewModel.HomeViewModel
import com.example.assignment1.viewModel.PetDetailsViewModel
import com.example.assignment1.viewModel.PetsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import com.example.assignment1.data.AppointmentRepository
import com.example.assignment1.data.AppointmentRepositoryImpl


@RequiresApi(Build.VERSION_CODES.O)
val appModules = module {
    single<PetRepository> { PetRepositoryImpl() }
    viewModel { PetsViewModel(get()) }
    viewModel { PetDetailsViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    single<GuidelineRepository> { GuidelineRepositoryImpl() }
    viewModel { GuidelineViewModel(get()) }
    viewModel { ExerciseViewModel() }
    viewModel { AppointmentViewModel(get()) }
    single<AppointmentRepository> { AppointmentRepositoryImpl() }
    viewModel { AppointmentViewModel(get()) }

}
