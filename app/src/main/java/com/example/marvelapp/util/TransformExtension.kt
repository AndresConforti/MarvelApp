package com.example.marvelapp.util

import com.example.marvelapp.database.entity.CharacterEntity
import com.example.marvelapp.entity.Character
import com.example.marvelapp.service.response.CharacterResponse
import com.example.marvelapp.service.response.DataResponse
import com.example.marvelapp.util.Constants.DOT

fun DataResponse.transformToList(): List<Character> {
    val characterList = mutableListOf<Character>()
    data.characters.forEach() {
        characterList.add(
            Character(it.id, it.name, it.description, "${it.img.path}$DOT${it.img.ext}")
        )
    }
    return characterList
}

fun CharacterResponse.toCharacter() = Character(
    this.id, this.name, this.description, "${this.img.path}$DOT${this.img.ext}"
)

fun CharacterEntity.toCharacter() = Character(this.id, this.name, this.description, this.img)

fun Character.toCharacterDB() = CharacterEntity(this.id, this.name, this.description, this.img)

fun List<CharacterEntity>.toCharacterList() = this.map { it.toCharacter() }
