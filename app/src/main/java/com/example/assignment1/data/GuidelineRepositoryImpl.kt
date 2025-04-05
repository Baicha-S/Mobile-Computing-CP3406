package com.example.assignment1.data

import com.example.assignment1.network.RetrofitClient

class GuidelineRepositoryImpl : GuidelineRepository {
    override suspend fun getGuidelines(): BreedResponse {
        return RetrofitClient.apiService.getBreeds()
    }
}