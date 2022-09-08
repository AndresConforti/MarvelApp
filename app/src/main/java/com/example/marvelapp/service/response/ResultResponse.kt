package com.example.marvelapp.service.response

import com.google.gson.annotations.SerializedName

data class ResultResponse(
    @SerializedName("results") val characters: MutableList<CharacterResponse>
)
