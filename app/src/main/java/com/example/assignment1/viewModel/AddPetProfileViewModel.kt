package com.example.assignment1.viewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment1.R // Import the R file
import com.example.assignment1.data.Pet
import com.example.assignment1.data.PetDao
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class AddPetProfileViewModel(private val petDao: PetDao) : ViewModel() {
    var petName by mutableStateOf("")
    var petDOB by mutableStateOf("")
    var petGender by mutableStateOf("")
    var petSpecies by mutableStateOf("")
    var petAllergies by mutableStateOf("")
    var imageResId by mutableIntStateOf(R.drawable.screenshot_2025_02_16_191358) // Hardcoded ImageResId
    var showError by mutableStateOf(false)

    var saveSuccess by mutableStateOf(false)
        private set
    var saveError by mutableStateOf<String?>(null)
        private set

    @RequiresApi(Build.VERSION_CODES.O)
    fun savePet() {
        showError = true
        if (petName.isEmpty() || petDOB.isEmpty() || petGender.isEmpty() || petSpecies.isEmpty() || petAllergies.isEmpty()) {
            return
        }
        viewModelScope.launch {
            try {
                val dob = LocalDate.parse(petDOB, DateTimeFormatter.ISO_LOCAL_DATE)
                val newPet = Pet(
                    name = petName,
                    petDOB = dob,
                    gender = petGender,
                    species = petSpecies,
                    allergies = petAllergies,
                    imageResId = imageResId
                )
                petDao.insertPet(newPet)
                saveSuccess = true
                saveError = null
                showError = false
                // Clear the fields after successful save
                petName = ""
                petDOB = ""
                petGender = ""
                petSpecies = ""
                petAllergies = ""
                imageResId = R.drawable.screenshot_2025_02_16_191358
            } catch (e: Exception) {
                saveSuccess = false
                saveError = e.message
            }
        }
    }
}