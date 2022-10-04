package com.example.di

import androidx.room.Room
import com.example.data.database.MarvelDB
import org.koin.dsl.module

object DBModule {
    private const val DB = "MarvelRepository"

    val dbModule = module {
        single { Room.databaseBuilder(get(), MarvelDB::class.java, DB).build() }
        single { get<MarvelDB>().marvelDAO() }
    }
}
