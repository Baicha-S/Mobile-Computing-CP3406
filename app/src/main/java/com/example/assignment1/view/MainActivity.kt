package com.example.assignment1.view

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.*
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.assignment1.R
import com.example.assignment1.navigation.AppNavigation
import com.example.assignment1.ui.theme.Assignment1Theme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Assignment1Theme {
                MainScreen()  // now wrapped in your custom theme with custom font
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    var selectedItem by remember { mutableStateOf("home") }
    // navigation bar icons
    val homeIcon = painterResource(id = R.drawable.paw_png79)
    val calendarIcon = painterResource(id = R.drawable.calendar_icon_png_transparent_3)
    val addPetIcon = painterResource(id = R.drawable.plus_icon)
    val exerciseIcon = painterResource(id = R.drawable.exercise_icon_png_16)
    val guidelineIcon = painterResource(id = R.drawable.guideline_icon)

    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme.copy(
            background = Color(0xFFFAF0E6)
        )
    ) {
        Scaffold(
            modifier = Modifier,
            topBar = { TopBar() },
            bottomBar = {
                Column {
                    Divider(
                        color = Color(0xFFD1BEA8),
                        thickness = 3.dp,
                        modifier = Modifier.shadow(
                            elevation = 10.dp,
                        ))
                    NavigationBar(
                        containerColor = Color(0xFFFAF0E6),
                        modifier = Modifier
                    ) {
                        NavigationBarItem( // Home(Paw)
                            icon = {
                                Image(
                                    painter = homeIcon,
                                    contentDescription = "Home",
                                    modifier = Modifier.size(30.dp)
                                )
                            },
                            selected = selectedItem == "home",
                            onClick = {
                                selectedItem = "home"
                                navController.navigate("home") {
                                    popUpTo("home") { inclusive = true }
                                }
                            }
                        )
                        NavigationBarItem( // calendar
                            icon = {
                                Image(
                                    painter = calendarIcon,
                                    contentDescription = "calendar",
                                    modifier = Modifier.size(30.dp)
                                )
                            },
                            selected = selectedItem == "appointments",
                            onClick = {
                                selectedItem = "appointments"
                                navController.navigate("appointments") {
                                    popUpTo("home") { inclusive = false }
                                }
                            }
                        )
                        NavigationBarItem( // add pet
                            icon = {
                                Image(
                                    painter = addPetIcon,
                                    contentDescription = "addPet",
                                    modifier = Modifier.size(40.dp)
                                )
                            },
                            selected = selectedItem == "add_pet",
                            onClick = {
                                selectedItem = "add_pet"
                                navController.navigate("add_pet") {
                                    popUpTo("home") { inclusive = false }
                                }
                            }
                        )
                        NavigationBarItem( //exercise
                            icon = {
                                Image(
                                    painter = exerciseIcon,
                                    contentDescription = "exercise",
                                    modifier = Modifier.size(30.dp)
                                )
                            },
                            selected = selectedItem == "exercise",
                            onClick = {
                                selectedItem = "exercise"
                                navController.navigate("exercise") {
                                    popUpTo("home") { inclusive = false }
                                }
                            }
                        )
                        NavigationBarItem( //guideline
                            icon = {
                                Image(
                                    painter = guidelineIcon,
                                    contentDescription = "guideline",
                                    modifier = Modifier.size(30.dp)
                                )
                            },
                            selected = selectedItem == "guideline",
                            onClick = {
                                selectedItem = "guideline"
                                navController.navigate("guideline") {
                                    popUpTo("home") { inclusive = false }
                                }
                            }
                        )
                    }
                }
            }
        ) { innerPadding ->
            AppNavigation(navController = navController, innerPadding = innerPadding)
        }
    }
}


@Composable
fun TopBar() {
    val settingIcon = painterResource(id = R.drawable.settings_icon)
    val findIcon = painterResource(id = R.drawable.find_icon)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 25.dp, horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = settingIcon,
            contentDescription = "Settings Icon",
            modifier = Modifier.size(30.dp)
        )
        Image(
            painter = findIcon,
            contentDescription = "Find Icon",
            modifier = Modifier.size(30.dp)
        )
    }
}

