package com.example.marvelapp.mapper

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.marvelapp.entity.Character
import com.example.marvelapp.util.toCharacterDB
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
        character = Character(ID, NAME, DESCRIPTION)
    }

    @Test
    fun `transforming a Character entity to a CharacterDB entity`() {
        character.toCharacterDB()

        Assert.assertEquals(ID, character.id)
        Assert.assertEquals(NAME, character.name)
        Assert.assertEquals(DESCRIPTION, character.description)
    }

    companion object {
        const val ID = "616"
        const val NAME = "Spider-Man"
        const val DESCRIPTION = "With great power there must also come great responsibility."
    }
}
