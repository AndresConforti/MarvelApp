package com.example.marvelapp.di

import com.example.marvelapp.service.request.generator.MarvelRequestGenerator
import org.koin.dsl.module

object ApiModule {
    val apiModule = module {
        factory { MarvelRequestGenerator() }
    }
}
