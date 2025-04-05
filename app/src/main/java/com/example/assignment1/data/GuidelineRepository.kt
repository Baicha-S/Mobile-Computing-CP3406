package com.example.assignment1.data

interface GuidelineRepository {
    suspend fun getGuidelines(): BreedResponse
}
