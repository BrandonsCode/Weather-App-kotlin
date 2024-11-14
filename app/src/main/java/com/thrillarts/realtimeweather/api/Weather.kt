package com.thrillarts.realtimeweather.api

data class Weather(
    val description: String,
    val icon: String,
    val id: String,
    val main: String
)