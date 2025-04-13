package com.example.assignment1.viewModel
/*
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.assignment1.data.Pet
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
class ExerciseViewModel : ViewModel() {

    private val _petList = mutableStateListOf<Pet>()
    val petList: List<Pet> = _petList

    init {
        // Add test data for debugging
        _petList.add(Pet(1, "Buddy", "Dog", 0, LocalDate.now().minusYears(2), "Male", "None"))
        _petList.add(Pet(2, "Whiskers", "Cat", 0, LocalDate.now().minusYears(1), "Female", "Pollen"))
        Log.d("ExerciseViewModel", "ViewModel initialized. petList size: ${_petList.size}")
    }

    fun addPet(pet: Pet) {
        _petList.add(pet)
        Log.d("ExerciseViewModel", "Pet added: ${pet.name}, petList size: ${_petList.size}")
    }

    fun setExerciseGoal(petId: Int, hours: Float) {
        val petIndex = _petList.indexOfFirst { it.id == petId }
        if (petIndex != -1) {
            _petList[petIndex] = _petList[petIndex].copy(exerciseGoalHours = hours)
            Log.d("ExerciseViewModel", "Goal set for pet $petId: $hours")
        }
    }

    fun addExerciseProgress(petId: Int, hours: Float) {
        val petIndex = _petList.indexOfFirst { it.id == petId }
        if (petIndex != -1) {
            _petList[petIndex] = _petList[petIndex].copy(exerciseProgressHours = _petList[petIndex].exerciseProgressHours + hours)
            Log.d("ExerciseViewModel", "Progress added for pet $petId: $hours")
        }
    }
}
*/