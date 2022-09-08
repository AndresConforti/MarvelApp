package com.example.marvelapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.marvelapp.mvvm.viewmodel.MainViewModel
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    private var mainViewModel = MainViewModel()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Test
    fun `set livedata with PRESSED_BUTTON as value`() {
        mainViewModel.onPressedButton()

        Assert.assertEquals(
            MainViewModel.ButtonState.PRESSED_BUTTON,
            mainViewModel.buttonState.value?.buttonPressed
        )
    }
}
