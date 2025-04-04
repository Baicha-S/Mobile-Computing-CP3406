package com.example.assignment1.di

import com.example.assignment1.data.GuidelineRepository
import com.example.assignment1.data.GuidelineRepositoryImpl
import com.example.assignment1.data.PetRepository
import com.example.assignment1.data.PetRepositoryImpl
import com.example.assignment1.viewModel.GuidelineViewModel
import com.example.assignment1.viewModel.HomeViewModel
import com.example.assignment1.viewModel.PetDetailsViewModel
import com.example.assignment1.viewModel.PetsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModules = module {
    single<PetRepository> { PetRepositoryImpl() }
    viewModel { PetsViewModel(get()) }
    viewModel { PetDetailsViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    single<GuidelineRepository> { GuidelineRepositoryImpl() }
    viewModel { GuidelineViewModel(get()) }
}

