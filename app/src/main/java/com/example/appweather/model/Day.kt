package com.example.appweather.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Day(
    @SerialName("datetime") val datetime: String,
    @SerialName("tempmax") val tempmax: Double,
    @SerialName("tempmin") val tempmin: Double,
    @SerialName("temp") val temp: Double,
    @SerialName("description") val description: String,
    @SerialName("icon") val icon: String
)