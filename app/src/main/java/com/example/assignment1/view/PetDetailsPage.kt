package com.example.assignment1.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import com.example.assignment1.data.MedicalInfo
import com.example.assignment1.viewModel.PetDetailsViewModel
import org.koin.androidx.compose.koinViewModel
import com.example.assignment1.data.calculatePetAge
import com.example.assignment1.navigation.Screens
import java.text.SimpleDateFormat
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PetDetailsPage(petId: Int, navController: NavController) {
    val viewModel: PetDetailsViewModel = koinViewModel()

    LaunchedEffect(petId) {
        viewModel.loadPetDetails(petId)
    }

    val pet by viewModel.petDetails.observeAsState()
    val medicalHistory by viewModel.medicalHistory.observeAsState(initial = emptyList())

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
                    .size(150.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = it.name,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(color = Color(0xFFC3B091))
                    .padding(16.dp)
            ) {
                Text(text = "Age: ${calculatePetAge(it.petDOB)}")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Gender: ${it.gender}")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Species: ${it.species}")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Allergies: ${it.allergies}")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Medical History",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start)
            )

            MedicalHistoryTable(medicalHistory = medicalHistory)

            IconButton(
                onClick = {
                    navController.navigate(Screens.AddMedicalInfoScreen.route.replace("{petId}", petId.toString()))
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add Medical History")
            }


            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.popBackStack() },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC3B091)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "Back to Home", color = Color.White)
            }
        }
    } ?: run {
        Text(text = "Pet details not found")
    }
}

@Composable
fun MedicalHistoryTable(medicalHistory: List<MedicalInfo>) {
    LazyColumn(modifier = Modifier.heightIn(max = 200.dp)) {
        items(medicalHistory) { info ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = formatDate(info.date), modifier = Modifier.weight(1f))
                Text(text = info.clinicName, modifier = Modifier.weight(1f))
                Text(text = info.vetName, modifier = Modifier.weight(1f))
                Text(text = info.description, modifier = Modifier.weight(2f))
            }
            Divider()
        }
    }
}

fun formatDate(date: java.util.Date): String {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return dateFormat.format(date)
}
