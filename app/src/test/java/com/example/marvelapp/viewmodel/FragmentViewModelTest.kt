package com.example.marvelapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.marvelapp.entity.Character
import com.example.marvelapp.mvvm.model.FragmentModel
import com.example.marvelapp.mvvm.viewmodel.FragmentViewModel
import com.example.marvelapp.testObserver
import com.example.marvelapp.usecase.GetCharacterUseCase
import com.example.marvelapp.util.Result
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

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class FragmentViewModelTest {
    private val testDispatcher = TestCoroutineDispatcher()

    private lateinit var viewModel: FragmentViewModel
    private lateinit var model: FragmentModel
    private val getCharacterUseCase: GetCharacterUseCase = mock()
    private lateinit var character: Character
    private val exception: Exception = mock()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        Dispatchers.setMain(testDispatcher)
        model = FragmentModel(getCharacterUseCase)
        viewModel = FragmentViewModel(model)
        character = Character(ID, NAME, DESCRIPTION, IMG)
    }

    @After
    fun after() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `Checking if getCharacter returns a success state`() {
        val liveData = viewModel.fragmentState.testObserver()

        whenever(getCharacterUseCase(ID)).thenReturn(Result.Success(character))

        runBlocking {
            viewModel.getCharacter(character.id).join()
        }

        assertEquals(
            FragmentViewModel.FragmentState.RESPONSE_SUCCESS,
            liveData.observedValues[0]?.state
        )
    }

    @Test
    fun `Checking if getCharacter returns an error state`() {
        val liveData = viewModel.fragmentState.testObserver()

        whenever(getCharacterUseCase(ID)).thenReturn(Result.Failure(exception = exception))

        runBlocking {
            viewModel.getCharacter(character.id).join()
        }

        assertEquals(
            FragmentViewModel.FragmentState.RESPONSE_ERROR,
            liveData.observedValues[0]?.state
        )
    }

    @Test
    fun `Checking if onTouchListener returns a dismiss state`() {
        val liveData = viewModel.fragmentState.testObserver()

        runBlocking {
            viewModel.onTouchListener()
        }

        assertEquals(
            FragmentViewModel.FragmentState.DISMISS_FRAGMENT,
            liveData.observedValues[0]?.state
        )
    }

    companion object {
        const val ID = "1011347"
        const val NAME = "Spider-Man"
        const val DESCRIPTION = "With great power there must also come great responsibility."
        const val IMG = "http://terrigen-cdn-dev.marvel.com/content/prod/1x/203ham_com_crd_01.jpg"
    }
}
