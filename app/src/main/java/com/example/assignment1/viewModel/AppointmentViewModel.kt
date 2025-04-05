package com.example.assignment1.viewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment1.data.Appointment
import com.example.assignment1.data.AppointmentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class AppointmentViewModel(private val appointmentRepository: AppointmentRepository) : ViewModel() {

    private val _appointmentList = MutableStateFlow<List<Appointment>>(emptyList())
    val appointmentList: StateFlow<List<Appointment>> = _appointmentList

    init {
        viewModelScope.launch {
            _appointmentList.value = appointmentRepository.getAllAppointments()
        }
    }

    fun addAppointment(appointment: Appointment) {
        viewModelScope.launch {
            appointmentRepository.insertAppointment(appointment)
            _appointmentList.value = appointmentRepository.getAllAppointments() // Fetch updated list
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getAppointmentsForDate(date: LocalDateTime): List<Appointment> {
        return _appointmentList.value.filter { it.dateTime.toLocalDate() == date.toLocalDate() }
    }
}