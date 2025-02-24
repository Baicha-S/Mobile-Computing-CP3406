package com.example.assignment1

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController

data class Pet(val name: String, val imageResId: Int)
val pet1 = Pet("Pet 1", R.drawable.screenshot_2025_02_16_191358 )
val pet2 = Pet("Pet 2", R.drawable.screenshot_2025_02_16_191409)

@Composable
fun HomePage(padding: Modifier, navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 40.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Home",
            fontFamily = FontFamily.Serif,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(32.dp))
        PetProfileBox(pet1, navController) // Pass navController
        Spacer(modifier = Modifier.height(16.dp))
        PetProfileBox(pet2, navController) // Pass navController
        Spacer(modifier = Modifier.height(16.dp))
        AddPetProfileBox(navController) // Pass navController
    }
}

val BoxColor = Color(0xFFD1BEA8) // #d1bea8

@Composable
fun PetProfileBox(pet: Pet, navController: NavController) {
    Box(
        modifier = Modifier
            .background(color = BoxColor, shape = RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .height(150.dp)
            .padding(16.dp)
            .clickable {
                // Navigate to Pet Details page, passing pet name as argument
                navController.navigate("pet_details/${pet.name}") {
                    popUpTo("home") { inclusive = false }
                }
            },
        contentAlignment = Alignment.TopStart,
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = pet.imageResId),
                    contentDescription = pet.name,
                    modifier = Modifier
                        .size(48.dp) // Adjust size as needed
                        .clip(CircleShape) // Clip to a circle
                        .background(Color.White)
                        .padding(4.dp)
                        .size(40.dp),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(16.dp)) // space between image and text

                Text(
                    text = pet.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
            Text(
                text = "Up coming appointment"
            )
            Text(
                text = "Grooming next 2 weeks"
            )
        }
    }

}



@Composable
fun AddPetProfileBox(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(color = Color(0xFFE0E0E0), shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
            .clickable {
                navController.navigate("add_pet") {
                    popUpTo("home") { inclusive = false }
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Add pet profile",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = "+",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}
