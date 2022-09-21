package com.example.marvelapp.database

import com.example.marvelapp.entity.Character
import com.example.marvelapp.util.Result
import com.example.marvelapp.util.toCharacterDB
import com.example.marvelapp.util.toCharacterList

class MarvelRepositoryImpl(private val charactersDao: MarvelDao) : MarvelRepository {

    override fun getDBCharacters(): Result<List<Character>> =
        charactersDao.getDBCharacters().let {
            if (it.isNotEmpty()) {
                Result.Success(it.toCharacterList())
            } else {
                Result.Failure(Exception())
            }
        }

    override fun insertCharactersToDB(charactersList: List<Character>) {
        charactersList.forEach {
            charactersDao.insertCharacter(it.toCharacterDB())
        }
    }
}