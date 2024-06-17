package com.omar.domain.usecase

import com.omar.domain.model.CategoryResponseItem

sealed interface GetImagesStates

class Success(val image: List<CategoryResponseItem>):GetImagesStates

data object Error : GetImagesStates

//Loading

