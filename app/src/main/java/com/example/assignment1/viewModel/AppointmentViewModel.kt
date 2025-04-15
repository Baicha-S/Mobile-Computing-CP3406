package com.example.assignment1.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment1.data.Appointment
import com.example.assignment1.data.AppointmentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AppointmentViewModel(private val appointmentRepository: AppointmentRepository) : ViewModel() {

    private val _appointmentList = MutableStateFlow<List<Appointment>>(emptyList())
    val appointmentList: StateFlow<List<Appointment>> = _appointmentList

    init {
        viewModelScope.launch {
            appointmentRepository.getAllAppointments().collect { appointments ->
                _appointmentList.value = appointments
            }
        }
    }

    fun insertAppointment(appointment: Appointment) {
        Log.d("AppointmentViewModel", "insertAppointment called: $appointment")
        viewModelScope.launch {
            appointmentRepository.insertAppointment(appointment)
        }
    }

    fun deleteAppointment(appointment: Appointment) {
        viewModelScope.launch {
            appointmentRepository.deleteAppointment(appointment)
        }
    }
}