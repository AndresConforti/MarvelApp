package com.example.marvelapp.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.marvelapp.database.MarvelRepository
import com.example.marvelapp.entity.Character
import com.example.marvelapp.service.CharacterService
import com.example.marvelapp.util.Result
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class GetCharactersUseCaseImplTest {
    private var characterService: CharacterService = mock()
    private var db: MarvelRepository = mock()
    private val characterList: List<Character> = mock()
    private lateinit var getCharactersListUseCase: GetCharactersUseCase

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        getCharactersListUseCase = GetCharactersUseCaseImpl(characterService, db)
    }

    @Test
    fun `if usecase returns success`() {
        whenever(characterService.getCharacters()).thenReturn(Result.Success(characterList))
        whenever(db.getDBCharacters()).thenReturn(Result.Success(characterList))
        val result = getCharactersListUseCase()

        verify(db).insertCharactersToDB(characterList)
        verify(db).getDBCharacters()

        Assert.assertEquals(characterList, (result as Result.Success).data)
    }

    @Test
    fun `if usecase returns failure - empty DB`() {
        whenever(characterService.getCharacters()).thenReturn(Result.Failure(Exception(MSG)))
        whenever(db.getDBCharacters()).thenReturn(Result.Failure(Exception(MSG)))
        val result = getCharactersListUseCase()

        verify(db).getDBCharacters()

        Assert.assertEquals(MSG, (result as Result.Failure).exception.message)
    }

    @Test
    fun `if usecase returns failure - !empty DB`() {
        whenever(characterService.getCharacters()).thenReturn(Result.Failure(Exception(MSG)))
        whenever(db.getDBCharacters()).thenReturn(Result.Success(characterList))
        val result = getCharactersListUseCase()

        verify(db).getDBCharacters()

        Assert.assertEquals(characterList, (result as Result.Success).data)
    }

    companion object {
        private const val MSG = "ERROR"
    }
}
