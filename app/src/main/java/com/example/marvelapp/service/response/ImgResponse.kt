package com.example.marvelapp.service.response

import com.google.gson.annotations.SerializedName

data class ImgResponse(
    @SerializedName("path") val path: String,
    @SerializedName("extension") val ext: String
)
