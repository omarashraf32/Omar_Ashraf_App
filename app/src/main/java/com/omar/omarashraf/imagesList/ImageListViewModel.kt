package com.omar.omarashraf.imagesList

import androidx.lifecycle.viewModelScope
import com.omar.domain.model.ImageModel
import com.omar.domain.usecase.getImagesList.GetImagesUseCase
import com.omar.domain.usecase.getImagesList.GetImagesResult
import com.omar.omarashraf.base.BaseIntent
import com.omar.omarashraf.base.BaseViewModel
import com.omar.omarashraf.imagesList.states.ImagesListIntents
import com.omar.omarashraf.imagesList.states.ImagesListStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageListViewModel @Inject constructor(
    private val getUpdateImages: GetImagesUseCase,
) : BaseViewModel() {


    override suspend fun handleIntent(intent: BaseIntent) {
        when (intent) {
            is ImagesListIntents.ResetIntent -> {}
            is ImagesListIntents.GetImages -> getImages()
        }
    }


    private fun getImages() {
        viewModelScope.launch {
            getUpdateImages.execute().collect { state ->
                when (state) {
                    is GetImagesResult.Success -> emitImages(state.image)
                    is GetImagesResult.Error -> emitError()
                }
            }
        }
    }

    private suspend fun emitImages(images: List<ImageModel>) =
        emit(ImagesListStates.GetImagesSuccess(images))

    private suspend fun emitError() = emit(ImagesListStates.GetImagesError)
}