package com.omar.domain.repo

import com.omar.domain.model.CategoryResponse

interface ImageRepo {
    //RestApi
   suspend fun getImageFromRemote(): CategoryResponse

}