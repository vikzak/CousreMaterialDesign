package ru.gb.cousrematerialdesign.remoterepository

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PictureOfTheDayAPI {
    @GET("planetary/apod")
    fun getPictureOfTheDay(
        @Query("api_key") apiKey:String
    ): Call<PictureOfTheDayResponceData>

    @GET("planetary/apod")
    fun getPictureOfTheDay(
        @Query("api_key") apiKey:String,
        @Query("date")date: String
    ): Call<PictureOfTheDayResponceData>


    @GET("/mars-photos/api/v1/rovers/curiosity/photos")
    fun getMarsImageByDate(
        @Query("earth_date") earth_date: String,
        @Query("api_key") apiKey: String,
    ): Call<MarsPhotosServerResponseData>

    @GET("DONKI/FLR")
    fun getSolarFlare(
        @Query("api_key") apiKey: String,
        @Query("startDate") startDate: String,
    ): Call<List<SolarSystemResponseData>>

}