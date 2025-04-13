package com.example.assignment1.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.assignment1.view.AddAppointmentPage
import com.example.assignment1.view.AddPetProfilePage
import com.example.assignment1.view.AppointmentPage
//import com.example.assignment1.view.ExercisePage
//import com.example.assignment1.view.GuidelinePage
import com.example.assignment1.view.HomePage
import com.example.assignment1.view.PetDetailsPage
import com.example.assignment1.view.PetList
import com.example.assignment1.viewModel.AddPetProfileViewModel
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.HomeScreen.route) {
        composable(Screens.HomeScreen.route) { HomePage(navController) }
        composable(Screens.AppointmentScreen.route) { AppointmentPage(navController) }
        composable(Screens.AddAppointmentScreen.route) { AddAppointmentPage(navController) }
        composable(Screens.AddPetScreen.route) { innerPadding ->
            val viewModel: AddPetProfileViewModel = koinViewModel()
            AddPetProfilePage(navController, viewModel)
        }
        //composable(Screens.ExerciseScreen.route) { ExercisePage(Modifier.padding(innerPadding)) }
        //composable(Screens.GuidelineScreen.route) { GuidelinePage(Modifier.padding(innerPadding)) }
        composable(Screens.PetDetailsScreen.route) { backStackEntry ->
            val petId = backStackEntry.arguments?.getString("petId")?.toIntOrNull() ?: 0
            PetDetailsPage(petId = petId, navController)
        }
        composable(Screens.PetListScreen.route) { PetList(Modifier.fillMaxSize()) }
    }
}