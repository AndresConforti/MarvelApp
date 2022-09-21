package com.example.marvelapp.database

import com.example.marvelapp.entity.Character
import com.example.marvelapp.util.Result

interface MarvelRepository {
    fun getDBCharacters(): Result<List<Character>>
    fun insertCharactersToDB(charactersList: List<Character>)
}