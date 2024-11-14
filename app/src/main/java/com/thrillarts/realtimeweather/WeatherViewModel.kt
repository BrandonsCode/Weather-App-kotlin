package com.thrillarts.realtimeweather

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thrillarts.realtimeweather.api.Constant
import com.thrillarts.realtimeweather.api.NetworkResponse
import com.thrillarts.realtimeweather.api.RetrofitInstance
import com.thrillarts.realtimeweather.api.WeatherModel
import kotlinx.coroutines.launch

class WeatherViewModel :ViewModel() {

    private val weatherApi = RetrofitInstance.weatherApi
    private  val _weatherResult = MutableLiveData<NetworkResponse<WeatherModel>>()
    private val weatherResult : LiveData<NetworkResponse<WeatherModel>> = _weatherResult

    fun getData(city: String){
        _weatherResult.value = NetworkResponse.Loading
        viewModelScope.launch {
//           val response = weatherApi.getWeather(Constant.City),
//            val response = weatherApi.getWeather(Constant.State),
            val response = weatherApi.getWeather(Constant.appid, city)
            if (response.isSuccessful) {
                try {
                    response.body()?.let {
                        _weatherResult.value = NetworkResponse.Success(it)
                    }
                }else{
                    _weatherResult.value = NetworkResponse.Error("Failed to load data")
                }

            }
            catch (e : Exception){
                _weatherResult.value = NetworkResponse.Error("Failed to load data")
            }
        }
    }
}