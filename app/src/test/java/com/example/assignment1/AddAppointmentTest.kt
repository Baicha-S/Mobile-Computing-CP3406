package com.example.assignment1

import com.example.assignment1.data.Appointment
import com.example.assignment1.data.AppointmentRepository
import com.example.assignment1.viewModel.AppointmentViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class AppointmentViewModelTest {

    @Test
    fun `init should load all appointments from repository`() = runTest {
        val mockRepository = mock(AppointmentRepository::class.java)
        val testAppointments = listOf(
            Appointment(
                id = 1,
                description = "Check-up",
                location = "Clinic A",
                dateTime = java.time.LocalDateTime.now()
            ),
            Appointment(
                id = 2,
                description = "Vaccination",
                location = "Clinic B",
                dateTime = java.time.LocalDateTime.now().plusDays(7)
            )
        )
        `when`(mockRepository.getAllAppointments()).thenReturn(flowOf(testAppointments))

        val viewModel = AppointmentViewModel(mockRepository)

        assertEquals(testAppointments, viewModel.appointmentList.value)
    }

    @Test
    fun `insertAppointment should call insertAppointment on the repository`() = runTest {
        val mockRepository = mock(AppointmentRepository::class.java)
        val viewModel = AppointmentViewModel(mockRepository)
        val testAppointment = Appointment(
            id = 3,
            description = "Follow-up",
            location = "Clinic A",
            dateTime = java.time.LocalDateTime.now().plusDays(1)
        )

        viewModel.insertAppointment(testAppointment)

        verify(mockRepository).insertAppointment(testAppointment)
    }

    @Test
    fun `deleteAppointment should call deleteAppointment on the repository`() = runTest {
        val mockRepository = mock(AppointmentRepository::class.java)
        val viewModel = AppointmentViewModel(mockRepository)
        val testAppointment = Appointment(
            id = 4,
            description = "Surgery",
            location = "Hospital X",
            dateTime = java.time.LocalDateTime.now().plusMonths(1)
        )

        viewModel.deleteAppointment(testAppointment)

        verify(mockRepository).deleteAppointment(testAppointment)
    }
}