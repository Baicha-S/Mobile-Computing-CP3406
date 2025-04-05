package com.example.assignment1.network

import com.example.assignment1.data.BreedResponse
import retrofit2.http.GET

interface GuidelineApiService {
    @GET("breeds")
    suspend fun getBreeds(): BreedResponse
}
