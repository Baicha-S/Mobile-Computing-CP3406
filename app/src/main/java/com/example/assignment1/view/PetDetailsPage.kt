package com.example.assignment1.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assignment1.data.Pet
import com.example.assignment1.viewModel.PetDetailsViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PetDetailsPage(petId: Int) {
    val viewModel: PetDetailsViewModel = koinViewModel(parameters = { parametersOf(petId) })
    val pet by viewModel.pet.observeAsState()
    val petAge by viewModel.petAge.observeAsState()

    pet?.let { pet ->
        PetDetailsContent(pet = pet, petAge = petAge)
    } ?: run {
        Text(text = "Loading Pet Details...")
    }
}

@Composable
fun PetDetailsContent(pet: Pet, petAge: String?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 60.dp, horizontal = 16.dp),
    ) {
        // Circular Image
        Image(
            painter = painterResource(id = pet.imageResId),
            contentDescription = pet.name,
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
                .align(Alignment.CenterHorizontally),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        // White Box for Info
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFC3B091), shape = RoundedCornerShape(8.dp))
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    text = pet.name,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Species: ${pet.species}", fontSize = 18.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Age: ${petAge ?: "Age Unknown"}", fontSize = 18.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Gender: ${pet.gender}", fontSize = 18.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Allergies: ${pet.allergies}", fontSize = 18.sp)
            }
        }
    }
}
