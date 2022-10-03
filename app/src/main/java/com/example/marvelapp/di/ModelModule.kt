package com.example.marvelapp.di

import com.example.marvelapp.mvvm.model.CharactersModel
import com.example.marvelapp.mvvm.model.FragmentModel
import org.koin.dsl.module

object ModelModule {
    val modelModule = module {
        factory { CharactersModel(get()) }
        factory { FragmentModel(get()) }
    }
}
