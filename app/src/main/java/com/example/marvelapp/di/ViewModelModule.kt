package com.example.marvelapp.di

import com.example.marvelapp.mvvm.viewmodel.CharactersViewModel
import com.example.marvelapp.mvvm.viewmodel.FragmentViewModel
import com.example.marvelapp.mvvm.viewmodel.MainViewModel
import com.example.marvelapp.mvvm.viewmodel.SplashScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ViewModelModule {
    val viewModelModule = module {
        viewModel { SplashScreenViewModel() }
        viewModel { MainViewModel() }
        viewModel { CharactersViewModel(get()) }
        viewModel { FragmentViewModel(get()) }
    }
}
