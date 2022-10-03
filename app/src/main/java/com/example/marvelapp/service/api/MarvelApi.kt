package com.example.marvelapp.service.api

import com.example.marvelapp.service.response.CharacterResponse
import com.example.marvelapp.service.response.DataResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MarvelApi {
    @GET("/v1/public/characters")
    fun getCharacters(): Call<DataResponse>

    @GET("/v1/public/characters/characterId")
    fun getCharacter(@Path("characterId") id: String): Call<CharacterResponse>
}
