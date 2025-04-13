package com.example.assignment1.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assignment1.data.BreedData
import com.example.assignment1.viewModel.GuidelineViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun GuidelinePage(modifier: Modifier = Modifier, viewModel: GuidelineViewModel = koinViewModel()) {
    val breedResponse by viewModel.guidelines.observeAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 60.dp, horizontal = 16.dp),
        horizontalAlignment = Alignment.Start
    ){
        Text(
            text = "Tips about pet",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
            )
        breedResponse?.let { response ->
            LazyColumn(
                modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(response.data) { breedData ->
                    GuidelineCard(breedData)
                }
            }
        } ?: run {
            Text(text = "Could not load data. Please check your internet connection.")
        }
    }
}


@Composable
fun GuidelineCard(breedData: BreedData) {
    Box(
        modifier = Modifier.fillMaxWidth()
            .background(Color(0xFFC3B091))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = breedData.attributes.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(8.dp))
            //Image loading or placeholder here.
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = breedData.attributes.description)
        }
    }
}
