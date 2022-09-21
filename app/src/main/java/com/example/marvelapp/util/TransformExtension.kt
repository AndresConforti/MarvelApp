package com.example.marvelapp.util

import com.example.marvelapp.database.entity.CharacterEntity
import com.example.marvelapp.entity.Character
import com.example.marvelapp.service.response.DataResponse

fun DataResponse.transformToList(): List<Character> {
    val characterList = mutableListOf<Character>()
    data.characters.forEach() { characterList.add(Character(it.id, it.name, it.description)) }
    return characterList
}

fun CharacterEntity.toCharacter() = Character(this.id, this.name, this.description)

fun Character.toCharacterDB() = CharacterEntity(this.id, this.name, this.description)

fun List<CharacterEntity>.toCharacterList() = this.map { it.toCharacter() }
