package com.example.marvelapp.util

import com.example.marvelapp.entity.Character
import com.example.marvelapp.service.response.DataResponse

fun DataResponse.transformToList(): List<Character> {
    val characterList = mutableListOf<Character>()
    data.characters.forEach() {
        characterList.add(
            Character(
                it.id,
                it.name,
                it.description
            )
        )
    }
    return characterList
}
