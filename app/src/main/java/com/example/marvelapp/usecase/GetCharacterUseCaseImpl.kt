package com.example.marvelapp.usecase

import com.example.marvelapp.database.MarvelRepository
import com.example.marvelapp.entity.Character
import com.example.marvelapp.service.CharacterService
import com.example.marvelapp.util.Result

interface GetCharacterUseCase {
    operator fun invoke(characterId: String): Result<Character>
}

class GetCharacterUseCaseImpl(
    private val characterService: CharacterService,
    private val db: MarvelRepository
) : GetCharacterUseCase {
    override operator fun invoke(id: String): Result<Character> {
        return when (val serviceResult = characterService.getCharacter(id)) {
            is Result.Success -> {
                db.insertCharacterToDB(serviceResult.data)
                db.getCharacter(serviceResult.data.id)
            }
            is Result.Failure -> db.getCharacter(id)
        }
    }
}
