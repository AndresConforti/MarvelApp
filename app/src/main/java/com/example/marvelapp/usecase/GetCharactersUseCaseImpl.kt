package com.example.marvelapp.usecase

import com.example.marvelapp.database.MarvelRepository
import com.example.marvelapp.entity.Character
import com.example.marvelapp.service.CharacterService
import com.example.marvelapp.util.Result

interface GetCharactersUseCase {
    operator fun invoke(): Result<List<Character>>
}

class GetCharactersUseCaseImpl(
    private val characterService: CharacterService,
    private val db: MarvelRepository
) : GetCharactersUseCase {
    override operator fun invoke(): Result<List<Character>> {
        return when (val serviceResult = characterService.getCharacters()) {
            is Result.Success -> {
                db.insertCharactersToDB(serviceResult.data)
                db.getDBCharacters()
            }
            is Result.Failure -> db.getDBCharacters()
        }
    }
}
