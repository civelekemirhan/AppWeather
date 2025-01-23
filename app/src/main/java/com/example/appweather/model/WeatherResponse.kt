package com.example.appweather.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class WeatherResponse(
    @SerialName("queryCost") val queryCost: Int,
    @SerialName("latitude") val latitude: Double,
    @SerialName("longitude") val longitude: Double,
    @SerialName("resolvedAddress") val resolvedAddress: String,
    @SerialName("address") val address: String,
    @SerialName("timezone") val timezone: String,
    @SerialName("tzoffset") val tzoffset: Double,
    @SerialName("days") val days: List<Day>
)
