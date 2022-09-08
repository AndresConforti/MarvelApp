package com.example.marvelapp.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.marvelapp.service.CharacterService
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class GetCharactersUseCaseImplTest {
    private var characterService: CharacterService = mock()
    private lateinit var getCharactersListUseCase: GetCharactersUseCaseImpl

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        getCharactersListUseCase = GetCharactersUseCaseImpl(characterService)
    }

    @Test
    fun `get characters from CharacterService test`() {
        getCharactersListUseCase.invoke()
        verify(characterService).getCharacters()
    }
}
