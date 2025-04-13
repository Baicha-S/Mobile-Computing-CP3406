package com.example.assignment1.view

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.assignment1.viewModel.AddPetProfileViewModel
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType

// Global constants for styling
val boxColor = Color(0xFFD1BEA8)

@SuppressLint("UnrememberedMutableState")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddPetProfilePage(
    navController: NavHostController,
    viewModel: AddPetProfileViewModel
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 80.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextFieldWithLabel(
            value = viewModel.petName,
            onValueChange = { viewModel.petName = it },
            label = "Pet Name",
            errorMessage = if (viewModel.petName.isEmpty() && viewModel.showError) "Name cannot be empty" else null
        )
        TextFieldWithLabel(
            value = viewModel.petDOB,
            onValueChange = { viewModel.petDOB = it },
            label = "Birthday (YYYY-MM-DD)",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            errorMessage = if (viewModel.petDOB.isEmpty() && viewModel.showError) "Birthday cannot be empty" else null
        )
        TextFieldWithLabel(
            value = viewModel.petGender,
            onValueChange = { viewModel.petGender = it },
            label = "Gender",
            errorMessage = if (viewModel.petGender.isEmpty() && viewModel.showError) "Gender cannot be empty" else null
        )
        TextFieldWithLabel(
            value = viewModel.petSpecies,
            onValueChange = { viewModel.petSpecies = it },
            label = "Species",
            errorMessage = if (viewModel.petSpecies.isEmpty() && viewModel.showError) "Species cannot be empty" else null
        )
        TextFieldWithLabel(
            value = viewModel.petAllergies,
            onValueChange = { viewModel.petAllergies = it },
            label = "Allergies",
            errorMessage = if (viewModel.petAllergies.isEmpty() && viewModel.showError) "Allergies cannot be empty" else null
        )
        TextFieldWithLabel(
            value = if (viewModel.imageResId == 0) "" else viewModel.imageResId.toString(),
            onValueChange = {
                viewModel.imageResId = it.toIntOrNull() ?: 0 // Handle invalid input
            },
            label = "Image Resource ID",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            errorMessage = if (viewModel.imageResId == 0 && viewModel.showError) "Image ID cannot be empty" else null
        )

        Spacer(modifier = Modifier.height(16.dp))
        SaveButton {
            viewModel.savePet()
            navController.popBackStack() // Navigate back to the previous screen
        }

        if (viewModel.saveSuccess) {
            Text("Pet saved successfully!")
        }

        if (viewModel.saveError != null) {
            Text(viewModel.saveError!!, color = Color.Red)
        }
    }
}

@Composable
fun TextFieldWithLabel(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    readOnly: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    errorMessage: String? = null
) {
    Column {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            modifier = Modifier.fillMaxWidth().then(modifier),
            readOnly = readOnly,
            keyboardOptions = keyboardOptions,
            isError = errorMessage != null
        )
        if (errorMessage != null) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun SaveButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = boxColor,
            contentColor = contentColorFor(boxColor)
        )
    ) {
        Text(text = "Save", fontWeight = FontWeight.Bold, fontSize = 16.sp)
    }
}