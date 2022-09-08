package com.example.marvelapp.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private var _buttonState: MutableLiveData<ButtonData> = MutableLiveData()
    val buttonState: LiveData<ButtonData> get() = _buttonState

    fun onPressedButton() {
        _buttonState.value = ButtonData(ButtonState.PRESSED_BUTTON)
    }

    data class ButtonData(
        val buttonPressed: ButtonState
    )

    enum class ButtonState {
        PRESSED_BUTTON
    }
}
