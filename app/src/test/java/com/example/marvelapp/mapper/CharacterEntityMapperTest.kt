package com.example.marvelapp.mapper

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.marvelapp.database.entity.CharacterEntity
import com.example.marvelapp.util.toCharacter
import com.example.marvelapp.util.toCharacterList
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CharacterEntityMapperTest {
    private lateinit var characterEntity: CharacterEntity
    private lateinit var characterEntityList: List<CharacterEntity>

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        characterEntity = CharacterEntity(ID, NAME, DESCRIPTION, IMG)
        characterEntityList = mutableListOf(CharacterEntity(ID, NAME, DESCRIPTION, IMG))
    }

    @Test
    fun `transforming a Character entity list to a CharacterDB entity list`() {
        val dbListToCharacterList = characterEntityList.toCharacterList()

        assertEquals(ID, dbListToCharacterList[0].id)
        assertEquals(NAME, dbListToCharacterList[0].name)
        assertEquals(DESCRIPTION, dbListToCharacterList[0].description)
        assertEquals(IMG, dbListToCharacterList[0].img)
    }

    @Test
    fun `transforming a CharacterDB entity to a Character entity`() {
        val dbToCharacter = characterEntity.toCharacter()

        assertEquals(ID, dbToCharacter.id)
        assertEquals(NAME, dbToCharacter.name)
        assertEquals(DESCRIPTION, dbToCharacter.description)
        assertEquals(IMG, dbToCharacter.img)
    }

    companion object {
        const val ID = "1011347"
        const val NAME = "Spider-Man"
        const val DESCRIPTION = "With great power there must also come great responsibility."
        const val IMG = "http://terrigen-cdn-dev.marvel.com/content/prod/1x/203ham_com_crd_01.jpg"
    }
}
