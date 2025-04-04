package com.example.assignment1.network

import com.example.assignment1.data.BreedResponse
import retrofit2.http.GET

interface GuidelineApiService {
    @GET("breeds") // Replace with your API endpoint
    suspend fun getBreeds(): BreedResponse
}