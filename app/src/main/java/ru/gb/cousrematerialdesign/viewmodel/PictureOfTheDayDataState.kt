package ru.gb.cousrematerialdesign.viewmodel

import ru.gb.cousrematerialdesign.remoterepository.MarsPhotosServerResponseData
import ru.gb.cousrematerialdesign.remoterepository.PictureOfTheDayResponceData
import ru.gb.cousrematerialdesign.remoterepository.SolarSystemResponseData

sealed class PictureOfTheDayDataState {
    data class Success(val serverResponseData: PictureOfTheDayResponceData) : PictureOfTheDayDataState()
    data class Error(val error: Throwable) : PictureOfTheDayDataState()
    //data class Loading(val progress: Int?) : PictureOfTheDayDataState()
    data class SuccessMars(val serverResponseData: MarsPhotosServerResponseData) : PictureOfTheDayDataState()
    data class SuccessWeather(val solarFlareResponseData:List<SolarSystemResponseData>) : PictureOfTheDayDataState()
    //data class SuccessPOD(val serverResponseData: PODServerResponseData) : AppState()
    object Loading :  PictureOfTheDayDataState()
}