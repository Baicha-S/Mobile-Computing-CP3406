package com.example.assignment1.di

import com.example.assignment1.data.PetRepository
import com.example.assignment1.data.PetRepositoryImpl
import com.example.assignment1.viewModel.PetDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { (petId: Int) ->
        PetDetailsViewModel(petId, get())
    }
    single<PetRepository> { PetRepositoryImpl() } // Bind PetRepository to PetRepositoryImpl
}