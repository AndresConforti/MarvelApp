package com.example.marvelapp.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.Character
import com.example.marvelapp.mvvm.model.FragmentModel
import com.example.domain.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentViewModel(private val model: FragmentModel) : ViewModel() {

    private val _fragmentState: MutableLiveData<FragmentData> = MutableLiveData()
    val fragmentState: LiveData<FragmentData> get() = _fragmentState

    fun getCharacter(characterId: String) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            model.getCharacter(characterId)
        }.let { result ->
            when (result) {
                is Result.Success -> {
                    _fragmentState.postValue(
                        FragmentData(
                            state = FragmentState.RESPONSE_SUCCESS,
                            data = result.data
                        )
                    )
                }
                is Result.Failure -> {
                    _fragmentState.postValue(FragmentData(state = FragmentState.RESPONSE_ERROR))
                }
            }
        }
    }

    fun onTouchListener() {
        _fragmentState.postValue(FragmentData(state = FragmentState.DISMISS_FRAGMENT))
    }

    data class FragmentData(
        val state: FragmentState,
        val data: Character? = null
    )

    enum class FragmentState {
        RESPONSE_SUCCESS,
        RESPONSE_ERROR,
        DISMISS_FRAGMENT
    }
}
