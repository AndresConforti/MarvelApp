package com.example.marvelapp.service

import com.example.marvelapp.entity.Character
import com.example.marvelapp.service.api.MarvelApi
import com.example.marvelapp.service.request.generator.MarvelRequestGenerator
import com.example.marvelapp.util.Result
import com.example.marvelapp.util.transformToList

class CharacterServiceImpl(private val api: MarvelRequestGenerator) : CharacterService {

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
}
