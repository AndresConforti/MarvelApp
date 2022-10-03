package com.example.marvelapp.mapper

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.marvelapp.service.response.CharacterResponse
import com.example.marvelapp.service.response.DataResponse
import com.example.marvelapp.service.response.ImgResponse
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
class ResponseMapperTest {
    private lateinit var resultResponse: ResultResponse
    private lateinit var dataResponse: DataResponse
    private lateinit var imgResponse: ImgResponse

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        imgResponse = ImgResponse(PATH, EXT)
        resultResponse =
            ResultResponse(mutableListOf(CharacterResponse(ID, NAME, DESCRIPTION, imgResponse)))
        dataResponse = DataResponse(resultResponse)
    }

    @Test
    fun `transforming data response to character list`() {
        dataResponse.transformToList()

        assertEquals(ID, resultResponse.characters[0].id)
        assertEquals(NAME, resultResponse.characters[0].name)
        assertEquals(DESCRIPTION, resultResponse.characters[0].description)
        assertEquals(
            IMG,
            (resultResponse.characters[0].img.path) + (resultResponse.characters[0].img.ext)
        )
    }

    companion object {
        const val ID = "1011347"
        const val NAME = "Spider-Man"
        const val DESCRIPTION = "With great power there must also come great responsibility."
        const val PATH = "http://terrigen-cdn-dev.marvel.com/content/prod/1x/203ham_com_crd_01"
        const val EXT = ".jpg"
        const val IMG = PATH + EXT
    }
}
