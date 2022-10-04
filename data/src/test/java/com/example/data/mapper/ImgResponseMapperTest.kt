package com.example.data.mapper

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.data.service.response.ImgResponse
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ImgResponseMapperTest {
    private lateinit var imgResponse: ImgResponse

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        imgResponse = ImgResponse(
            ResponseMapperTest.PATH,
            ResponseMapperTest.EXT
        )
    }

    @Test
    fun `Checking if ImgResponse returns the right value`() {
        val path = imgResponse.path
        val ext = imgResponse.ext
        Assert.assertEquals(IMG, "$path$ext")
    }

    companion object {
        const val PATH = "http://terrigen-cdn-dev.marvel.com/content/prod/1x/203ham_com_crd_01"
        const val EXT = "jpg"
        const val IMG = "$PATH.$EXT"
    }
}
