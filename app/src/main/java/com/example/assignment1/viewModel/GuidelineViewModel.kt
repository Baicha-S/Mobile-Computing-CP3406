package com.example.assignment1.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment1.data.BreedResponse
import com.example.assignment1.data.GuidelineRepository
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class GuidelineViewModel(private val repository: GuidelineRepository) : ViewModel() {
    private val _guidelines = MutableLiveData<BreedResponse?>() // Make BreedResponse nullable
    val guidelines: LiveData<BreedResponse?> = _guidelines

    init {
        loadGuidelines()
    }

    private fun loadGuidelines() {
        viewModelScope.launch {
            try {
                _guidelines.value = repository.getGuidelines()
            } catch (e: UnknownHostException) {
                // Handle no internet connection
                _guidelines.value = null;
                // Log the error, or display a message to the user
            } catch (e: Exception) {
                // Handle other exceptions
                _guidelines.value = null;
                // Log the error, or display a message to the user
            }
        }
    }
}