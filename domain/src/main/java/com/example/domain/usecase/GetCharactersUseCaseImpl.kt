package com.example.domain.usecase

import com.example.domain.database.MarvelRepository
import com.example.domain.entity.Character
import com.example.domain.service.CharacterService
import com.example.domain.utils.Result

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
