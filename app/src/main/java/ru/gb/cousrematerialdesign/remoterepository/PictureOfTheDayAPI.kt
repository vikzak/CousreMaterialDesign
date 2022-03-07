package ru.gb.cousrematerialdesign.remoterepository

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PictureOfTheDayAPI {
    @GET("planetary/apod")
    fun getPictureOfTheDay(@Query("api_key") apiKey:String): Call<PictureOfTheDayResponceData>

    @GET("planetary/apod")
    fun getPictureOfTheDayForDate(@Query("api_key") apiKey:String, @Query("date")date: String): Call<PictureOfTheDayResponceData>
}