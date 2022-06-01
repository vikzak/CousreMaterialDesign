package ru.gb.cousrematerialdesign.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.gb.cousrematerialdesign.BuildConfig
import ru.gb.cousrematerialdesign.remoterepository.PictureOfTheDayRRI
import ru.gb.cousrematerialdesign.remoterepository.PictureOfTheDayResponceData

class PictureOfTheDayViewModel(
    private val liveData: MutableLiveData<PictureOfTheDayDataState> = MutableLiveData(),
    private val pictureOfTheDayRRI: PictureOfTheDayRRI = PictureOfTheDayRRI()
): ViewModel() {

    fun getLiveData(): LiveData<PictureOfTheDayDataState>{
        return liveData
    }

    fun sendServerRequest(){
        liveData.postValue(PictureOfTheDayDataState.Loading(null))
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
}