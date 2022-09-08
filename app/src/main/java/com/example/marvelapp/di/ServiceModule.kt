package com.example.marvelapp.di

import com.example.marvelapp.service.CharacterService
import com.example.marvelapp.service.CharacterServiceImpl
import org.koin.dsl.module

object ServiceModule {
    val serviceModule = module {
        factory<CharacterService> { CharacterServiceImpl(get()) }
    }
}
