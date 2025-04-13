package com.example.assignment1.navigation

sealed class Screens(val route: String) {
    object HomeScreen : Screens("home")
    object AppointmentScreen : Screens("appointments")
    object AddAppointmentScreen : Screens("addAppointment")
    object AddPetScreen : Screens("add_pet")
    object ExerciseScreen : Screens("exercise")
    object GuidelineScreen : Screens("guideline")
    object PetDetailsScreen : Screens("pet_details/{petId}")
    object PetListScreen : Screens("pet_list")
}
