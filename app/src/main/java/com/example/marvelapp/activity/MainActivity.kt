package com.example.marvelapp.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.marvelapp.databinding.ActivityMainBinding
import com.example.marvelapp.mvvm.viewmodel.MainViewModel
import org.koin.android.ext.android.inject
import org.koin.core.component.KoinComponent

class MainActivity : AppCompatActivity(), KoinComponent {
    private lateinit var binding: ActivityMainBinding
    val viewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.buttonState.observe({ lifecycle }, ::buttonState)
        binding.mainActivityButton.setOnClickListener { viewModel.onPressedButton() }
    }

    private fun buttonState(ButtonData: MainViewModel.ButtonData) {
        when (ButtonData.buttonPressed) {
            MainViewModel.ButtonState.PRESSED_BUTTON -> startActivity(
                CharactersActivity.newInstance(
                    this
                )
            )
        }
    }

    companion object {
        fun newInstance(context: Context): Intent = Intent(context, MainActivity::class.java)
    }
}
