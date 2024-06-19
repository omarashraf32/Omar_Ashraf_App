package com.omar.domain.model


import com.google.gson.annotations.SerializedName

data class ImageModel(
    @SerializedName("author")
    val author: String? = null,
    @SerializedName("download_url")
    val downloadUrl: String? = null,
    @SerializedName("height")
    val height: Int? = null,
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("width")
    val width: Int? = null
)