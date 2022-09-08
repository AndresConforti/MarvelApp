package com.example.marvelapp.mvvm.model

import com.example.marvelapp.entity.Character
import com.example.marvelapp.usecase.GetCharactersUseCase
import com.example.marvelapp.util.Result

class CharactersModel(private val getCharacterUseCase: GetCharactersUseCase) {
    fun getCharacters(): Result<List<Character>> = getCharacterUseCase()
}
