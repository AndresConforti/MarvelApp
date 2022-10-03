package com.example.marvelapp.usecase

import com.example.marvelapp.database.MarvelRepository
import com.example.marvelapp.entity.Character
import com.example.marvelapp.service.CharacterService
import com.example.marvelapp.util.Result
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class GetCharacterUseCaseImplTest {
    private var characterService: CharacterService = mock()
    private var db: MarvelRepository = mock()
    private lateinit var character: Character
    private lateinit var getCharacterUseCase: GetCharacterUseCase

    @Before
    fun init() {
        character = Character(ID, NAME, DESCRIPTION, IMG)
        getCharacterUseCase = GetCharacterUseCaseImpl(characterService, db)
    }

    @Test
    fun `if usecase returns success`() {
        whenever(characterService.getCharacter(ID)).thenReturn(Result.Success(character))
        whenever(db.getCharacter(ID)).thenReturn(Result.Success(character))

        val result = getCharacterUseCase(character.id)

        verify(db).insertCharacterToDB(character)
        verify(db).getCharacter(character.id)

        Assert.assertEquals(character, (result as Result.Success).data)
    }

    @Test
    fun `if usecase returns failure - !empty DB`() {
        whenever(characterService.getCharacter(ID)).thenReturn(Result.Failure(Exception(MSG)))
        whenever(db.getCharacter(ID)).thenReturn(Result.Success(character))
        val result = getCharacterUseCase(ID)

        verify(db).getCharacter(ID)

        Assert.assertEquals(character, (result as Result.Success).data)
    }

    @Test
    fun `if usecase returns failure - empty DB`() {
        whenever(characterService.getCharacter(ID)).thenReturn(Result.Failure(Exception(MSG)))
        whenever(db.getCharacter(ID)).thenReturn(Result.Failure(Exception(MSG)))
        val result = getCharacterUseCase(ID)

        verify(db).getCharacter(ID)

        Assert.assertEquals(MSG, (result as Result.Failure).exception.message)
    }

    companion object {
        const val ID = "1011347"
        const val NAME = "Spider-Man"
        const val DESCRIPTION = "With great power there must also come great responsibility."
        const val IMG = "http://terrigen-cdn-dev.marvel.com/content/prod/1x/203ham_com_crd_01.jpg"
        const val MSG = "ERROR"
    }
}
