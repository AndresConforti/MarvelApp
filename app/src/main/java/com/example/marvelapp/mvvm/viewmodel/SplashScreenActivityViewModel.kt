package com.example.marvelapp.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SplashScreenActivityViewModel : ViewModel() {
    private val mutableLiveData: MutableLiveData<SplashScreenData> = MutableLiveData()
    val splashScreenState: LiveData<SplashScreenData> get() = mutableLiveData

    fun splashDone() {
        mutableLiveData.value = SplashScreenData(SplashScreenState.DONE)
    }

    data class SplashScreenData(
        val state: SplashScreenState
    )

    enum class SplashScreenState {
        DONE
    }
}
