package com.example.marvelapp.mapper

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.marvelapp.service.response.CharacterResponse
import com.example.marvelapp.service.response.DataResponse
import com.example.marvelapp.service.response.ResultResponse
import com.example.marvelapp.util.transformToList
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MapperTest {
    private lateinit var resultResponse: ResultResponse
    private lateinit var dataResponse: DataResponse

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        resultResponse = ResultResponse(
            mutableListOf(
                CharacterResponse(ID, NAME, DESCRIPTION)
            )
        )
        dataResponse = DataResponse(resultResponse)
    }

    @Test
    fun `transforming data response to character list`() {
        val response = dataResponse.transformToList()

        assertEquals(ID, resultResponse.characters[0].id)
        assertEquals(NAME, resultResponse.characters[0].name)
        assertEquals(DESCRIPTION, resultResponse.characters[0].description)
    }

    companion object {
        const val ID = "616"
        const val NAME = "Spider-Man"
        const val DESCRIPTION = "With great power there must also come great responsibility."
    }
}
