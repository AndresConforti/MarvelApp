package com.example.marvelapp.service.api

import com.example.marvelapp.service.response.DataResponse
import retrofit2.Call
import retrofit2.http.GET

interface MarvelApi {
    @GET("/v1/public/characters")
    fun getCharacters(): Call<DataResponse>
}
