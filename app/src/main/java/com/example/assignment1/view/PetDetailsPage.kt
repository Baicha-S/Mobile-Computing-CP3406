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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
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
import com.example.assignment1.viewModel.HomeViewModel
import org.koin.androidx.compose.koinViewModel
import com.example.assignment1.data.calculatePetAge
import com.example.assignment1.navigation.Screens
import com.example.assignment1.ui.theme.BoxColor
import java.text.SimpleDateFormat
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PetDetailsPage(petId: Int, navController: NavController) {
    val viewModel: PetDetailsViewModel = koinViewModel()
    val homeViewModel: HomeViewModel = koinViewModel()

    LaunchedEffect(petId) {
        viewModel.loadPetDetails(petId)
    }

    val pet by viewModel.petDetails.observeAsState()
    val medicalHistory by viewModel.medicalHistory.observeAsState(initial = emptyList())
    var showDeleteDialog by remember { mutableStateOf(false) }

    pet?.let { petData ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 60.dp, horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = petData.name,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )

                Row {
                    IconButton(
                        onClick = {
                            navController.navigate(Screens.EditPetScreen.route.replace("{petId}", petId.toString()))
                        }
                    ) {
                        Icon(Icons.Filled.Edit, contentDescription = "Edit Pet")
                    }

                    IconButton(
                        onClick = { showDeleteDialog = true }
                    ) {
                        Icon(Icons.Filled.Delete, contentDescription = "Delete Pet")
                    }
                }
            }

            Image(
                painter = painterResource(id = petData.imageResId),
                contentDescription = petData.name,
                modifier = Modifier
                    .size(150.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.height(16.dp))

            Column(
                // General Info
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(color = BoxColor)
                    .padding(16.dp)
            ) {
                Text(text = "Age: ${calculatePetAge(petData.petDOB)}")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Gender: ${petData.gender}")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Species: ${petData.species}")
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Allergies: ${petData.allergies}")
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
                colors = ButtonDefaults.buttonColors(containerColor = BoxColor),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "Back to Home", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
        }

        if (showDeleteDialog) {
            AlertDialog(
                onDismissRequest = { showDeleteDialog = false },
                title = { Text("Delete Pet?") },
                text = { Text("Are you sure you want to delete ${petData.name}?") },
                confirmButton = {
                    TextButton(onClick = {
                        homeViewModel.deletePet(petData)
                        showDeleteDialog = false
                        navController.popBackStack()
                    }) {
                        Text("Delete")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDeleteDialog = false }) {
                        Text("Cancel")
                    }
                }
            )
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
        }
    }
}

fun formatDate(date: java.util.Date): String {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return dateFormat.format(date)
}
