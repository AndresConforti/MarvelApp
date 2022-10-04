package com.example.di

import com.example.domain.usecase.GetCharacterUseCase
import com.example.domain.usecase.GetCharacterUseCaseImpl
import com.example.domain.usecase.GetCharactersUseCase
import com.example.domain.usecase.GetCharactersUseCaseImpl
import org.koin.dsl.module

object UseCaseModule {
    val useCaseModule = module {
        factory<GetCharactersUseCase> { GetCharactersUseCaseImpl(get(), get()) }
        factory<GetCharacterUseCase> { GetCharacterUseCaseImpl(get(), get()) }
    }
}
