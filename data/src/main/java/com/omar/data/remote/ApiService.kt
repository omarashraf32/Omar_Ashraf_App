package com.omar.data.remote

import com.omar.domain.model.CategoryResponse
import retrofit2.http.GET

interface ApiService {
    @GET("list")
    fun getImage(): CategoryResponse
}