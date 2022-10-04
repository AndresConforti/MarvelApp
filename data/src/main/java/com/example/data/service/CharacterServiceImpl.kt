package com.example.data.service

import com.example.domain.entity.Character
import com.example.data.service.api.MarvelApi
import com.example.domain.utils.Result
import com.example.data.service.utils.toCharacter
import com.example.data.service.utils.transformToList
import com.example.domain.service.CharacterService

class CharacterServiceImpl(private val api: MarvelRequestGenerator) :
    CharacterService {

    override fun getCharacters(): Result<List<Character>> {
        try {
            val callResponse = api.createService(MarvelApi::class.java).getCharacters()
            val response = callResponse.execute()
            if (response.isSuccessful) response.body()?.let {
                return Result.Success(it.transformToList())
            }
        } catch (e: Exception) {
            return Result.Failure(Exception())
        }
        return Result.Failure(Exception())
    }

    override fun getCharacter(id: String): Result<Character> {
        try {
            val callResponse = api.createService(MarvelApi::class.java).getCharacter(id)
            val response = callResponse.execute()
            if (response.isSuccessful) response.body()?.let {
                return Result.Success(it.toCharacter())
            }
        } catch (e: Exception) {
            return Result.Failure(Exception())
        }
        return Result.Failure(Exception())
    }
}
