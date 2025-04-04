package com.example.assignment1.viewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.assignment1.data.Pet
import com.example.assignment1.data.PetRepository
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
class PetDetailsViewModel(private val petId: Int, private val petRepository: PetRepository) : ViewModel() {
    private val _pet = MutableLiveData<Pet?>()
    val pet: LiveData<Pet?> = _pet

    private val _petAge = MutableLiveData<String?>()
    val petAge: LiveData<String?> = _petAge

    init {
        loadPetDetails()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun loadPetDetails() {
        viewModelScope.launch {
            val pet = petRepository.getPetById(petId)
            _pet.value = pet
            pet?.petDOB?.let { dob ->
                _petAge.value = calculateAge(dob)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun calculateAge(dobString: String): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd") // Adjust format if needed
        val dob = LocalDate.parse(dobString, formatter)
        val today = LocalDate.now()
        val period = Period.between(dob, today)

        return when {
            period.years > 0 -> "${period.years} years, ${period.months} months"
            period.months > 0 -> "${period.months} months, ${period.days} days"
            else -> "${period.days} days"
        }
    }
}