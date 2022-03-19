package ru.gb.cousrematerialdesign.remoterepository

import com.google.gson.GsonBuilder
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class PictureOfTheDayRRI {
    companion object {
        private const val BASE_URL = "https://api.nasa.gov/"
    }

    private val api by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
            //.create(PictureOfTheDayAPI::class.java) // поставил как в примере
        retrofit.create(PictureOfTheDayAPI::class.java)
    }


    private val baseUrl = "https://api.nasa.gov/"

    fun getRetrofitImlp(): PictureOfTheDayAPI {
        return api
    }

    fun getPictureOfTheDay(apiKey: String, date: String, podCallback: Callback<PictureOfTheDayResponceData>) {
        api.getPictureOfTheDay(apiKey, date).enqueue(podCallback)
    }

    fun getMarsPictureByDate(earth_date: String, apiKey: String, marsCallbackByDate: Callback<MarsPhotosServerResponseData>) {
        api.getMarsImageByDate(earth_date, apiKey).enqueue(marsCallbackByDate)
    }

    fun getSolarFlare(apiKey: String, podCallback: Callback<List<SolarSystemResponseData>>, startDate:String="2021-09-07") {
        api.getSolarFlare(apiKey,startDate).enqueue(podCallback)
    }


//    private val baseUrl = "https://api.nasa.gov/"
//
//    fun getRetrofitImlp(): PictureOfTheDayAPI{
//        val retrofit = Retrofit.Builder()
//            .baseUrl(baseUrl)
//            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
//            .build()
//        return retrofit.create(PictureOfTheDayAPI::class.java)
//    }
}