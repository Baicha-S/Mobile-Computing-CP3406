package com.example.assignment1

import com.example.assignment1.data.MedicalInfo
import com.example.assignment1.data.MedicalInfoRepository
import com.example.assignment1.viewModel.AddMedicalInfoViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import java.util.Date

@ExperimentalCoroutinesApi
class AddMedicalInfoViewModelTest {

    @Test
    fun `addMedicalInfo should call insertMedicalInfo on the repository`(): Unit = runTest {
        val mockRepository = mock(MedicalInfoRepository::class.java)
        val viewModel = AddMedicalInfoViewModel(mockRepository)
        val testMedicalInfo = MedicalInfo(
            id = 1,
            petId = 10,
            date = Date(),
            clinicName = "Vet Clinic A",
            vetName = "Dr. Smith",
            description = "Routine check-up"
        )

        viewModel.addMedicalInfo(testMedicalInfo)
        verify(mockRepository).insertMedicalInfo(testMedicalInfo)
    }
}