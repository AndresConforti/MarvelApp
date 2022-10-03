package com.example.marvelapp.mvvm.model

import com.example.marvelapp.entity.Character
import com.example.marvelapp.usecase.GetCharacterUseCase
import com.example.marvelapp.util.Result

class FragmentModel(private val getCharacterUseCase: GetCharacterUseCase) {
    fun getCharacter(id: String): Result<Character> = getCharacterUseCase(id)
}
