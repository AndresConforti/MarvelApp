package com.example.marvelapp.di

import com.example.marvelapp.usecase.GetCharacterUseCase
import com.example.marvelapp.usecase.GetCharacterUseCaseImpl
import com.example.marvelapp.usecase.GetCharactersUseCase
import com.example.marvelapp.usecase.GetCharactersUseCaseImpl
import org.koin.dsl.module

object UseCaseModule {
    val useCaseModule = module {
        factory<GetCharactersUseCase> { GetCharactersUseCaseImpl(get(), get()) }
        factory<GetCharacterUseCase> { GetCharacterUseCaseImpl(get(), get()) }
    }
}
