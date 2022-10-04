package com.example.marvelapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.domain.entity.Character
import com.example.marvelapp.mvvm.model.CharactersModel
import com.example.marvelapp.mvvm.viewmodel.CharactersViewModel
import com.example.marvelapp.mvvm.viewmodel.CharactersViewModel.CharactersState
import com.example.marvelapp.testObserver
import com.example.domain.usecase.GetCharactersUseCase
import com.example.domain.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class CharactersViewModelTest {
    private val testDispatcher = TestCoroutineDispatcher()
    private lateinit var charactersViewModel: CharactersViewModel
    private lateinit var charactersModel: CharactersModel
    private val getCharacterUseCase: GetCharactersUseCase = mock()
    private val characters: List<Character> = mock()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        Dispatchers.setMain(testDispatcher)
        charactersModel = CharactersModel(getCharacterUseCase)
        charactersViewModel = CharactersViewModel(charactersModel)
    }

    @After
    fun after() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `set livedata with SHOW_CHARACTERS as value`() {
        val liveData = charactersViewModel.characterState.testObserver()
        whenever(getCharacterUseCase()).thenReturn(Result.Success(characters))

        runBlocking {
            charactersViewModel.getCharacters().join()
        }

        assertEquals(CharactersState.LOADING, liveData.observedValues[0]?.characterState)
        assertEquals(CharactersState.SHOW_CHARACTERS, liveData.observedValues[1]?.characterState)
        assertEquals(characters, liveData.observedValues[1]?.characterInformation)
    }

    @Test
    fun `set livedata with CHARACTERS_NOT_FOUND as value`() {
        val liveData = charactersViewModel.characterState.testObserver()
        whenever(getCharacterUseCase()).thenReturn(Result.Failure(Exception()))

        runBlocking {
            charactersViewModel.getCharacters().join()
        }

        assertEquals(CharactersState.LOADING, liveData.observedValues[0]?.characterState)
        assertEquals(
            CharactersState.CHARACTERS_NOT_FOUND,
            liveData.observedValues[1]?.characterState
        )
    }
}
