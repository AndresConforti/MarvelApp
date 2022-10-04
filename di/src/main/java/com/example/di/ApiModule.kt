package com.example.di

import com.example.data.service.MarvelRequestGenerator
import org.koin.dsl.module

object ApiModule {
    val apiModule = module {
        factory { MarvelRequestGenerator() }
    }
}
