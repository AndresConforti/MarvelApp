package com.example.data.database

import com.example.data.service.utils.toCharacter
import com.example.data.service.utils.toCharacterDB
import com.example.data.service.utils.toCharacterList
import com.example.domain.database.MarvelRepository
import com.example.domain.entity.Character
import com.example.domain.utils.Result


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

    override fun getCharacter(id: String): Result<Character> =
        charactersDao.getCharacter(id).let {
            if (it.isNotEmpty()) {
                Result.Success(it.first().toCharacter())
            } else {
                Result.Failure(Exception())
            }
        }

    override fun insertCharacterToDB(character: Character) {
        charactersDao.insertCharacter(character.toCharacterDB())
    }
}
