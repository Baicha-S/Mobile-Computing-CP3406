package com.example.assignment1.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.assignment1.viewModel.PetDetailsViewModel

@Composable
fun PetDetailsPage(petId: Int, viewModel: PetDetailsViewModel = viewModel()) {

    LaunchedEffect(petId) {
        viewModel.loadPetDetails(petId)
    }

    val pet by viewModel.petDetails.observeAsState()

    pet?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = it.imageResId),
                contentDescription = it.name,
                modifier = Modifier.size(200.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = it.name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Details: ...") // Replace with actual details
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Appointment: ...") // Replace with actual appointment details
        }
    } ?: run {
        Text(text = "Pet details not found.")
    }
}