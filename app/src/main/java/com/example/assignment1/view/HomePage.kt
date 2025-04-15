package com.example.assignment1.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.assignment1.data.Appointment
import com.example.assignment1.data.Pet
import com.example.assignment1.data.calculatePetAge
import com.example.assignment1.navigation.Screens
import com.example.assignment1.ui.theme.BoxColor
import com.example.assignment1.viewModel.AppointmentViewModel
import com.example.assignment1.viewModel.HomeViewModel
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomePage(navController: NavHostController, homeViewModel: HomeViewModel = koinViewModel()) {
    val pets by homeViewModel.pets.observeAsState(emptyList())
    val isLoadingPets by homeViewModel.isLoading.observeAsState(false)
    val errorPets by homeViewModel.error.observeAsState(null)

    val appointmentViewModel: AppointmentViewModel = koinViewModel()
    val appointments by appointmentViewModel.appointmentList.collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 60.dp)
    ) {
        Text(
            text = "PawTrack",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Text(
            text = "Your Pet",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        if (isLoadingPets) {
            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
        } else if (errorPets != null) {
            Text("Error loading pets: $errorPets", modifier = Modifier.padding(16.dp))
        } else {
            // Pet Profile Box
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(pets) { pet ->
                    PetProfileBox(pet, navController)
                }
            }
        }
        // Upcoming Appointments Section
        Text(
            text = "Upcoming Appointments",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        if (appointments.isEmpty()) {
            Text("No upcoming appointments.", modifier = Modifier.padding(horizontal = 16.dp))
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
            ) {
                items(appointments) { appointment ->
                    AppointmentListItem(appointment = appointment, onClick = {
                        navController.navigate(Screens.AppointmentScreen.route)
                    })
                    HorizontalDivider()
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PetProfileBox(pet: Pet, navController: NavController) {
    Box(
        modifier = Modifier
            .background(color = BoxColor, shape = RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .height(150.dp)
            .clickable {
                navController.navigate("pet_details/${pet.id}")
            },
        contentAlignment = Alignment.TopStart,
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = BoxColor, shape = RoundedCornerShape(8.dp))
                    .drawBehind {
                        drawRoundRect(
                            color = Color.Black.copy(alpha = 0.2f),
                            topLeft = Offset(0f, size.height - 2.dp.toPx()),
                            size = Size(size.width, 2.dp.toPx()),
                            cornerRadius = CornerRadius(4.dp.toPx())
                        )
                    }
                    .padding(8.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = pet.imageResId),
                            contentDescription = pet.name,
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Text(
                        text = pet.name,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(text = "Age: ${calculatePetAge(pet.petDOB)}")
                Text(text = "Species: ${pet.species}")
            }

        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppointmentListItem(appointment: Appointment, onClick: () -> Unit) {
    val backgroundColor = getAppointmentIndicatorColor(appointment.dateTime)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .background(color = backgroundColor.copy(alpha = 0.2f))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(appointment.description)
            Text(appointment.location)
            Text(appointment.dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun getAppointmentIndicatorColor(dateTime: LocalDateTime): Color {
    val now = LocalDateTime.now()
    val daysUntilAppointment = ChronoUnit.DAYS.between(now, dateTime)

    return when {
        dateTime.isBefore(now) -> Color.Red // Overdue
        daysUntilAppointment <= 7 -> Color.Yellow // Due within 7 days
        else -> Color.Green // Not yet due
    }
}
