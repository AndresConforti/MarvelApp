package com.example.domain.service

import com.example.domain.entity.Character
import com.example.domain.utils.Result

interface CharacterService {
    fun getCharacters(): Result<List<Character>>
    fun getCharacter(id: String): Result<Character>
}
