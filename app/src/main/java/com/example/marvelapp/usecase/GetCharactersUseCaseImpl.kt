package com.example.marvelapp.usecase

import com.example.marvelapp.entity.Character
import com.example.marvelapp.service.CharacterService
import com.example.marvelapp.service.CharacterServiceImpl
import com.example.marvelapp.util.Result
import org.koin.core.component.KoinComponent

interface GetCharactersUseCase {
    operator fun invoke(): Result<List<Character>>
}

class GetCharactersUseCaseImpl(private val characterService: CharacterService) :
    GetCharactersUseCase {
    override fun invoke(): Result<List<Character>> = characterService.getCharacters()
}
