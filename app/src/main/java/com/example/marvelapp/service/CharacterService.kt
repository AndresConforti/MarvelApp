package com.example.marvelapp.service

import com.example.marvelapp.entity.Character
import com.example.marvelapp.util.Result

interface CharacterService {
    fun getCharacters(): Result<List<Character>>
}
