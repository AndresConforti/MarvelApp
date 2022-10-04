package com.example.marvelapp.mvvm.model

import com.example.domain.entity.Character
import com.example.domain.usecase.GetCharacterUseCase
import com.example.domain.utils.Result

class FragmentModel(private val getCharacterUseCase: GetCharacterUseCase) {
    fun getCharacter(id: String): Result<Character> = getCharacterUseCase(id)
}
