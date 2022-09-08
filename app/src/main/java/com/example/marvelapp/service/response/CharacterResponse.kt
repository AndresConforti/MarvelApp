package com.example.marvelapp.service.response

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String
)
