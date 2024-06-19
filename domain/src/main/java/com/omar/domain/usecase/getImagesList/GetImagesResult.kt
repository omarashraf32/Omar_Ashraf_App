package com.omar.domain.usecase.getImagesList

import com.omar.domain.model.ImageModel

sealed interface GetImagesResult {
    class Success(val image: List<ImageModel>) : GetImagesResult

    data object Error : GetImagesResult
}