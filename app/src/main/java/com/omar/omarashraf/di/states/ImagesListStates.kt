package com.omar.omarashraf.di.states

import com.omar.domain.model.CategoryResponse
import com.omar.domain.model.CategoryResponseItem

sealed class ImagesListStates

data object InitState : ImagesListStates()

data object GetImagesLoading : ImagesListStates()

class GetImagesSuccess(val images: List<CategoryResponseItem>) : ImagesListStates()

data object GetImagesError : ImagesListStates()