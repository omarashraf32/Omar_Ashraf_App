package com.omar.data.repo

import com.omar.data.remote.ApiService
import com.omar.domain.model.CategoryResponse
import com.omar.domain.repo.ImageRepo

class ImageRepoImpl(private val apiService: ApiService): ImageRepo {
    override fun getImageFromRemote(): CategoryResponse = apiService.getImage()
}