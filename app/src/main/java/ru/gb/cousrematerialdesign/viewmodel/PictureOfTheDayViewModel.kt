package ru.gb.cousrematerialdesign.viewmodel

import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.gb.cousrematerialdesign.BuildConfig
import ru.gb.cousrematerialdesign.remoterepository.MarsPhotosServerResponseData
import ru.gb.cousrematerialdesign.remoterepository.PictureOfTheDayRRI
import ru.gb.cousrematerialdesign.remoterepository.PictureOfTheDayResponceData
import ru.gb.cousrematerialdesign.remoterepository.SolarSystemResponseData
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class PictureOfTheDayViewModel(
    private val liveData: MutableLiveData<PictureOfTheDayDataState> = MutableLiveData(),
    private val pictureOfTheDayRRI: PictureOfTheDayRRI = PictureOfTheDayRRI(),
//    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
//    private val retrofitImpl: RetrofitImpl = RetrofitImpl(),
): ViewModel() {

    fun getLiveData(): LiveData<PictureOfTheDayDataState>{
        return liveData
    }

    fun sendServerRequest(){
        liveData.postValue(PictureOfTheDayDataState.Loading)
        liveData.value = PictureOfTheDayDataState.Loading
        pictureOfTheDayRRI
            .getRetrofitImlp()
            .getPictureOfTheDay(BuildConfig.NASA_API_KEY)
            .enqueue(
                object : Callback<PictureOfTheDayResponceData>{
                    override fun onResponse(
                        call: Call<PictureOfTheDayResponceData>,
                        response: Response<PictureOfTheDayResponceData>
                    ) {
                        if (response.isSuccessful && response.body()!=null){
                            response.body()?.let {
                                liveData.postValue(PictureOfTheDayDataState.Success(it))
                            }
                        } else {
                            if (response.message().isNullOrEmpty())
                                liveData.value = PictureOfTheDayDataState.Error(Throwable("Unidentified error"))
                            else
                                liveData.value = PictureOfTheDayDataState.Error(Throwable(response.message()))
                        }
                    }

                    override fun onFailure(call: Call<PictureOfTheDayResponceData>, t: Throwable) {
                        liveData.value = PictureOfTheDayDataState.Error(t)
                    }
                }

            )

    }
    
    fun sendServerRequest(date:String){
        liveData.value = PictureOfTheDayDataState.Loading
        val apiKey: String = BuildConfig.NASA_API_KEY
        if (apiKey.isBlank()){
            //
            liveData.value = PictureOfTheDayDataState.Error(Throwable("wrong key"))
        } else {
            pictureOfTheDayRRI.getRetrofitImlp().getPictureOfTheDay(apiKey,date).enqueue(callback)
        }

    }

    private val callback = object : Callback<PictureOfTheDayResponceData>{
        override fun onResponse(
            call: Call<PictureOfTheDayResponceData>,
            response: Response<PictureOfTheDayResponceData>
        ) {
            if (response.isSuccessful&&response.body()!=null){
                liveData.value = PictureOfTheDayDataState.Success(response.body()!!)
            } else {
                liveData.value = PictureOfTheDayDataState.Error(IllegalStateException("Error"))
            }
        }

        override fun onFailure(call: Call<PictureOfTheDayResponceData>, t: Throwable) {
            liveData.value = PictureOfTheDayDataState.Error(IllegalStateException("On Failture"))
        }
    }

    fun getMarsPicture() {
        liveData.postValue(PictureOfTheDayDataState.Loading)
        val earthDate = getDayBeforeYesterday()
        pictureOfTheDayRRI.getMarsPictureByDate(earthDate,BuildConfig.NASA_API_KEY, marsCallback)
    }

    fun getDayBeforeYesterday(): String {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val yesterday = LocalDateTime.now().minusDays(2)
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            return yesterday.format(formatter)
        } else {
            val cal: Calendar = Calendar.getInstance()
            val s = SimpleDateFormat("yyyy-MM-dd")
            cal.add(Calendar.DAY_OF_YEAR, -2)
            return s.format(cal.time)
        }
    }

    val marsCallback = object : Callback<MarsPhotosServerResponseData> {

        override fun onResponse(
            call: Call<MarsPhotosServerResponseData>,
            response: Response<MarsPhotosServerResponseData>,
        ) {
            if (response.isSuccessful && response.body() != null) {
                liveData.postValue(PictureOfTheDayDataState.SuccessMars(response.body()!!))
            } else {
                val message = response.message()
                if (message.isNullOrEmpty()) {
                    liveData.postValue(PictureOfTheDayDataState.Error(Throwable(UNKNOWN_ERROR)))
                } else {
                    liveData.postValue(PictureOfTheDayDataState.Error(Throwable(message)))
                }
            }
        }

        override fun onFailure(call: Call<MarsPhotosServerResponseData>, t: Throwable) {
            liveData.postValue(PictureOfTheDayDataState.Error(t))
        }
    }

    fun getSolarFlare(day: Int){
        liveData.postValue(PictureOfTheDayDataState.Loading)
        val apiKey = BuildConfig.NASA_API_KEY
        if(apiKey.isBlank()){
            //
        }else{
            pictureOfTheDayRRI.getSolarFlare(apiKey,solarFlareCallback,getDate(day))
        }
    }

    private val solarFlareCallback  = object : Callback<List<SolarSystemResponseData>>{
        override fun onResponse(
            call: Call<List<SolarSystemResponseData>>,
            response: Response<List<SolarSystemResponseData>>
        ) {
            if(response.isSuccessful && response.body()!=null){
                liveData.postValue(PictureOfTheDayDataState.SuccessWeather(response.body()!!))
            }else{
                // ДЗ
            }
        }

        override fun onFailure(call: Call<List<SolarSystemResponseData>>, t: Throwable) {
            liveData.postValue(PictureOfTheDayDataState.Error(t))
        }
    }

    private fun getDate(day: Int): String {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val yesterday = LocalDateTime.now().minusDays(day.toLong())
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            return yesterday.format(formatter)
        } else {
            val cal: Calendar = Calendar.getInstance()
            val s = SimpleDateFormat("yyyy-MM-dd")
            cal.add(Calendar.DAY_OF_YEAR, (-day))
            return s.format(cal.time)
        }
    }

    companion object {
        private const val API_ERROR = "You need API Key"
        private const val UNKNOWN_ERROR = "Unidentified error"
    }

}


