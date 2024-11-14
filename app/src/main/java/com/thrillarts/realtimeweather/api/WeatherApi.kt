package com.thrillarts.realtimeweather.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("data/2.5/weather?q=")
    suspend fun getWeather(
        @Query("City") city : String,
        @Query("State") state : String,
        @Query("appid") appid : String
    ) : Response<WeatherModel>
}