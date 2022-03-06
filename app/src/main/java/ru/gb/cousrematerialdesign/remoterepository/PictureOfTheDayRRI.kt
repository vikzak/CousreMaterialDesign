package ru.gb.cousrematerialdesign.remoterepository

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class PictureOfTheDayRRI {
    private val baseUrl = "https://api.nasa.gov/"

    fun getRetrofitImlp(): PictureOfTheDayAPI{
         val retrofit = Retrofit.Builder()
             .baseUrl(baseUrl)
             .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
             .build()
        return retrofit.create(PictureOfTheDayAPI::class.java)
    }
}