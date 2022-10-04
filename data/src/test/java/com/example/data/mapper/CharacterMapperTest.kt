package com.example.data.mapper

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.domain.entity.Character
import com.example.data.service.utils.toCharacterDB
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CharacterMapperTest {
    private lateinit var character: Character

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        character = Character(ID, NAME, DESCRIPTION, IMG)
    }

    @Test
    fun `transforming a Character entity to a CharacterDB entity`() {
        character.toCharacterDB()

        Assert.assertEquals(ID, character.id)
        Assert.assertEquals(NAME, character.name)
        Assert.assertEquals(DESCRIPTION, character.description)
        Assert.assertEquals(IMG, character.img)
    }

    companion object {
        const val ID = "1011347"
        const val NAME = "Spider-Man"
        const val DESCRIPTION = "With great power there must also come great responsibility."
        const val IMG = "http://terrigen-cdn-dev.marvel.com/content/prod/1x/203ham_com_crd_01.jpg"
    }
}
