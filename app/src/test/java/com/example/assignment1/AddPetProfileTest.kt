package com.example.assignment1

import com.example.assignment1.data.Pet
import com.example.assignment1.data.PetDao
import com.example.assignment1.viewModel.AddPetProfileViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@ExperimentalCoroutinesApi
class AddPetProfileViewModelTest {

    @Test
    fun `savePet should insert pet into dao and set saveSuccess on valid input`() = runTest {
        val mockPetDao = mock(PetDao::class.java)
        val viewModel = AddPetProfileViewModel(mockPetDao)

        viewModel.petName = "Buddy"
        viewModel.petDOB = "2023-01-15"
        viewModel.petGender = "Male"
        viewModel.petSpecies = "Dog"
        viewModel.petAllergies = "None"

        viewModel.savePet()

        verify(mockPetDao).insertPet(
            Pet(
                name = "Buddy",
                petDOB = LocalDate.parse("2023-01-15", DateTimeFormatter.ISO_LOCAL_DATE),
                gender = "Male",
                species = "Dog",
                allergies = "None",
                imageResId = R.drawable.screenshot_2025_02_16_191358
            )
        )
        assertTrue(viewModel.saveSuccess)
        assertNull(viewModel.saveError)
        assertEquals(false, viewModel.showError)
        assertEquals("", viewModel.petName)
        assertEquals("", viewModel.petDOB)
        assertEquals("", viewModel.petGender)
        assertEquals("", viewModel.petSpecies)
        assertEquals("", viewModel.petAllergies)
        assertEquals(R.drawable.screenshot_2025_02_16_191358, viewModel.imageResId)
    }

    @Test
    fun `savePet should set showError and not insert pet on empty fields`() = runTest {

        val mockPetDao = mock(PetDao::class.java)
        val viewModel = AddPetProfileViewModel(mockPetDao)

        viewModel.savePet()

        verify(mockPetDao, never()).insertPet(any())
        assertTrue(viewModel.showError)
        assertEquals(false, viewModel.saveSuccess)
    }

    @Test
    fun `savePet should set saveError on invalid date format`() = runTest {
        val mockPetDao = mock(PetDao::class.java)
        val viewModel = AddPetProfileViewModel(mockPetDao)

        viewModel.petName = "Buddy"
        viewModel.petDOB = "invalid-date"
        viewModel.petGender = "Male"
        viewModel.petSpecies = "Dog"
        viewModel.petAllergies = "None"

        viewModel.savePet()

        verify(mockPetDao, never()).insertPet(any())
        assertEquals(false, viewModel.saveSuccess)
        assertTrue(
            viewModel.saveError?.contains("Text 'invalid-date' could not be parsed") ?: false
        )
        assertTrue(viewModel.showError)
    }
}


fun <T> any(): T {
    org.mockito.Mockito.any<T>()
    return uninitialized()
}

private fun <T> uninitialized(): T = null as T