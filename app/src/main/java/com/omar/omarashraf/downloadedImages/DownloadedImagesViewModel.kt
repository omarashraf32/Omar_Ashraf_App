package com.omar.omarashraf.downloadedImages

import androidx.lifecycle.viewModelScope
import com.omar.domain.usecase.getCachedImages.GetCachedImagesUseCase
import com.omar.domain.usecase.getCachedImages.GetDownloadedImagesResult
import com.omar.omarashraf.base.BaseIntent
import com.omar.omarashraf.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class DownloadedImagesViewModel @Inject constructor(
    private val getCachedImagesUseCase: GetCachedImagesUseCase
) : BaseViewModel() {

    override suspend fun handleIntent(intent: BaseIntent) {
        when (intent) {
            is DownloadedImagesIntents.GetDownloadedImages -> getDownloadedImages()
        }
    }


    private fun getDownloadedImages() {
        viewModelScope.launch {
            getCachedImagesUseCase.execute().collect { state ->
                when (state) {
                    is GetDownloadedImagesResult.Success -> onSuccess(state.images)

                    is GetDownloadedImagesResult.Error -> onError()
                }
            }
        }
    }

    private fun onError() {
        emit(DownloadedImagesStates.Error)

    }

    private fun onSuccess(images: List<File>) {
        emit(DownloadedImagesStates.SuccessImages(images))
    }
}