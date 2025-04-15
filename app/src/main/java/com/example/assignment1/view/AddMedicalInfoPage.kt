package com.example.assignment1.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.assignment1.data.MedicalInfo
import com.example.assignment1.ui.theme.BoxColor
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
            .padding(horizontal = 16.dp, vertical = 60.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            "Add Medical History",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = date.toString(),
            onValueChange = { /* Handle date input */ },
            label = { Text("Date") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = clinicName,
            onValueChange = { clinicName = it; clinicNameError = false },
            label = { Text("Clinic Name") },
            isError = clinicNameError,
            supportingText = { if (clinicNameError) Text("Clinic Name cannot be empty") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = vetName,
            onValueChange = { vetName = it; vetNameError = false },
            label = { Text("Vet Name") },
            isError = vetNameError,
            supportingText = { if (vetNameError) Text("Vet Name cannot be empty") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = description,
            onValueChange = { description = it; descriptionError = false },
            label = { Text("Description") },
            isError = descriptionError,
            supportingText = { if (descriptionError) Text("Description cannot be empty") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = BoxColor),
            onClick = {
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
            Text(
                "Add Medical History",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}