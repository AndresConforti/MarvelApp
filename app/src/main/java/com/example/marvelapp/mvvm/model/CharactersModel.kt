package com.example.marvelapp.mvvm.model

import com.example.domain.entity.Character
import com.example.domain.usecase.GetCharactersUseCase
import com.example.domain.utils.Result

class CharactersModel(private val getCharacterUseCase: GetCharactersUseCase) {
    fun getCharacters(): Result<List<Character>> = getCharacterUseCase()
}
