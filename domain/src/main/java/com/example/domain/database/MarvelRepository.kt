package com.example.domain.database

import com.example.domain.entity.Character
import com.example.domain.utils.Result

interface MarvelRepository {
    fun getDBCharacters(): Result<List<Character>>
    fun getCharacter(id: String): Result<Character>
    fun insertCharactersToDB(charactersList: List<Character>)
    fun insertCharacterToDB(character: Character)
}
