package com.omar.omarashraf

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omar.domain.model.CategoryResponseItem
import com.omar.domain.usecase.GetCachedImageUseCase
import com.omar.domain.usecase.GetUpdateImagesUseCase
import com.omar.domain.usecase.Success
import com.omar.omarashraf.di.states.GetImagesError
import com.omar.omarashraf.di.states.GetImagesLoading
import com.omar.omarashraf.di.states.GetImagesSuccess
import com.omar.omarashraf.di.states.ImagesListStates
import com.omar.omarashraf.di.states.InitState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageViewModel
@Inject constructor(
    private val getUpdateImages: GetUpdateImagesUseCase,
    private val getCachedImages: GetCachedImageUseCase
) : ViewModel() {

    private val _categories: MutableStateFlow<ImagesListStates> = MutableStateFlow(InitState)
    val categories: StateFlow<ImagesListStates?> = _categories

    fun getImages() {
        getCachedImages()
        getUpdateImages()
    }

    private fun getCachedImages() {
        viewModelScope.launch {
            _categories.emit(GetImagesLoading)
            getCachedImages.execute().collect{ state ->
                when(state){
                    is Success -> emitImages(state.image)
                    com.omar.domain.usecase.Error -> {}
                }

            }
        }
    }

    fun getUpdateImages() {
        viewModelScope.launch {
            getUpdateImages.execute().collect { state ->
                when (state) {
                    is Success -> emitImages(state.image)
                    com.omar.domain.usecase.Error -> emitError()
                }

            }
        }
    }

    private suspend fun emitImages(images: List<CategoryResponseItem>) =
        _categories.emit(GetImagesSuccess(images))

    private suspend fun emitError() = _categories.emit(GetImagesError)
}