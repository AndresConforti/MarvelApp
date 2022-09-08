package com.example.marvelapp.di

import com.example.marvelapp.mvvm.model.CharactersModel
import org.koin.dsl.module

object ModelModule {
    val modelModule = module {
        factory { CharactersModel(get()) }
    }
}
