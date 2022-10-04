package com.example.domain.usecase

import com.example.domain.database.MarvelRepository
import com.example.domain.entity.Character
import com.example.domain.service.CharacterService
import com.example.domain.utils.Result

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
