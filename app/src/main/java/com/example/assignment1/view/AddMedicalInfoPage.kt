package com.example.assignment1.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.assignment1.data.MedicalInfo
import com.example.assignment1.viewModel.AddMedicalInfoViewModel
import org.koin.androidx.compose.koinViewModel
import java.util.Date

@Composable
fun AddMedicalInfoPage(
    petId: Int,
    navController: NavController,
    viewModel: AddMedicalInfoViewModel = koinViewModel()
) {
    var date by remember { mutableStateOf(Date()) }
    var clinicName by remember { mutableStateOf("") }
    var vetName by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    var clinicNameError by remember { mutableStateOf(false) }
    var vetNameError by remember { mutableStateOf(false) }
    var descriptionError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = date.toString(),
            onValueChange = { /* Handle date input */ },
            label = { Text("Date") }
        )
        OutlinedTextField(
            value = clinicName,
            onValueChange = { clinicName = it; clinicNameError = false },
            label = { Text("Clinic Name") },
            isError = clinicNameError,
            supportingText = { if (clinicNameError) Text("Clinic Name cannot be empty") }
        )
        OutlinedTextField(
            value = vetName,
            onValueChange = { vetName = it; vetNameError = false },
            label = { Text("Vet Name") },
            isError = vetNameError,
            supportingText = { if (vetNameError) Text("Vet Name cannot be empty") }
        )
        OutlinedTextField(
            value = description,
            onValueChange = { description = it; descriptionError = false },
            label = { Text("Description") },
            isError = descriptionError,
            supportingText = { if (descriptionError) Text("Description cannot be empty") }
        )

        Button(onClick = {
            clinicNameError = clinicName.isEmpty()
            vetNameError = vetName.isEmpty()
            descriptionError = description.isEmpty()

            if (!clinicNameError && !vetNameError && !descriptionError) {
                val medicalInfo = MedicalInfo(
                    petId = petId,
                    date = date,
                    clinicName = clinicName,
                    vetName = vetName,
                    description = description
                )
                viewModel.addMedicalInfo(medicalInfo)
                navController.popBackStack()
            }
        }) {
            Text("Add Medical History")
        }
    }
}