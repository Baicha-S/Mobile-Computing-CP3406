package com.example.assignment1.data

import com.google.gson.annotations.SerializedName

data class BreedResponse(
    val data: List<BreedData>,
    val meta: Meta,
    val links: Links
)

data class BreedData(
    val id: String,
    val type: String,
    val attributes: BreedAttributes
)

data class BreedAttributes(
    val name: String,
    val description: String,
    val hypoallergenic: Boolean,
    val life: Life,
    @SerializedName("male_weight") val maleWeight: Weight,
    @SerializedName("female_weight") val femaleWeight: Weight
)

data class Life(
    val min: Int,
    val max: Int
)

data class Weight(
    val min: Int,
    val max: Int
)

data class Meta(
    val pagination: Pagination
)

data class Pagination(
    val current: Int,
    val records: Int
)

data class Links(
    val self: String,
    val current: String,
    val next: String,
    val last: String
)
