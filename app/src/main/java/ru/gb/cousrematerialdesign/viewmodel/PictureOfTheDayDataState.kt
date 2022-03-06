package ru.gb.cousrematerialdesign.viewmodel

import ru.gb.cousrematerialdesign.remoterepository.PictureOfTheDayResponceData

sealed class PictureOfTheDayDataState {
    data class Success(val serverResponseData: PictureOfTheDayResponceData) : PictureOfTheDayDataState()
    data class Error(val error: Throwable) : PictureOfTheDayDataState()
    data class Loading(val progress: Int?) : PictureOfTheDayDataState()
}