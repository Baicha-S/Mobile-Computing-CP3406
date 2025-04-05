package com.example.assignment1.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.assignment1.viewModel.PetDetailsViewModel
import org.koin.androidx.compose.koinViewModel
import com.example.assignment1.data.calculatePetAge

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PetDetailsPage(petId: Int, navController: NavController) {
    val viewModel: PetDetailsViewModel = koinViewModel()

    LaunchedEffect(petId) {
        viewModel.loadPetDetails(petId)
    }

    val pet by viewModel.petDetails.observeAsState()

    pet?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 60.dp, horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = it.imageResId),
                contentDescription = it.name,
                modifier = Modifier
                    .size(200.dp)
                    .clip(RoundedCornerShape(10.dp)), // Clip image
                contentScale = ContentScale.Crop, // Crop Image to fit
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = it.name,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))

            Column(modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(color = Color(0xFFC3B091))
                .padding(16.dp)){

                Text(text = "Age: ${calculatePetAge(it.petDOB)}")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Gender: ${it.gender}")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Species: ${it.species}")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Allergies: ${it.allergies}")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Appointment: ...")
            }
            Text(
                text = "Enter Medical History of ${it.name}",
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { navController.popBackStack() },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC3B091)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "Add History", color = Color.White)
            }

            Button(
                onClick = { navController.popBackStack() },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC3B091)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "Back to Home")
            }
        }
    } ?: run {
        Text(text = "Pet details not found") // When pet list is empty
    }
}
