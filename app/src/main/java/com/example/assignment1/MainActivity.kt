package com.example.assignment1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    var selectedItem by remember { mutableStateOf("home") }
    val navigationBarColor = Color(0xFFC3B091)

    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme.copy(
            background = Color(0xFFFAF0E6)

        )
    ) {
        Scaffold(
            bottomBar = {
                NavigationBar(
                    containerColor = navigationBarColor
                ) {
                    NavigationBarItem(
                        icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
                        label = { Text("Home") },
                        selected = selectedItem == "home",
                        onClick = {
                            selectedItem = "home"
                            navController.navigate("home") {
                                // Avoid building up a large backstack
                                popUpTo("home") { inclusive = true }
                            }
                        }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Filled.Notifications, contentDescription = "Appointments") },
                        label = { Text("Appointments") },
                        selected = selectedItem == "appointments",
                        onClick = {
                            selectedItem = "appointments"
                            navController.navigate("appointments") {
                                popUpTo("home") { inclusive = false } // Allow back navigation
                            }
                        }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Filled.Add, contentDescription = "Add Pet") },
                        label = { Text("Add Pet") },
                        selected = selectedItem == "add_pet",
                        onClick = {
                            selectedItem = "add_pet"
                            navController.navigate("add_pet") {
                                popUpTo("home") { inclusive = false } // Allow back navigation
                            }
                        }
                    )
                    NavigationBarItem(
                        icon = { Icon(Icons.Filled.Face, contentDescription = "Pet Details") },
                        label = { Text("Pet Details") },
                        selected = selectedItem == "pet_details",
                        onClick = {
                            selectedItem = "pet_details"
                            navController.navigate("pet_details") {
                                popUpTo("home") { inclusive = false } // Allow back navigation
                            }
                        }
                    )
                }
            }
        ) { innerPadding ->
            NavHost(navController = navController, startDestination = "home") {
                composable("home") { HomePage(Modifier.padding(innerPadding), navController) } // Pass navController here
                composable("appointments") { AppointmentPage(Modifier.padding(innerPadding)) }
                composable("add_pet") { AddPetProfilePage(Modifier.padding(innerPadding), navController) } // Pass navController here
                composable("pet_details") { PetDetailsPage(Modifier.padding(innerPadding)) }
            }
        }
    }
}

@Composable
fun HomeScreen(padding: Modifier) {

}

