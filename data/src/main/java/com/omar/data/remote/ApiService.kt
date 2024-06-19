package com.omar.data.remote

import com.omar.domain.model.ImageModel
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET("list")
    suspend fun getImagesList(): List<ImageModel>

    @GET
    suspend fun downloadImage(@Url imageUrl: String): ResponseBody
}