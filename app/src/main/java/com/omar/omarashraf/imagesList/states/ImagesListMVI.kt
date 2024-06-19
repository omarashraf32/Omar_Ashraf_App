package com.omar.omarashraf.imagesList.states

import com.omar.domain.model.ImageModel
import com.omar.omarashraf.base.BaseIntent
import com.omar.omarashraf.base.BaseState

sealed interface ImagesListStates : BaseState {
    data object InitState : ImagesListStates
    data object Loading : ImagesListStates
    class GetImagesSuccess(val images: List<ImageModel>) : ImagesListStates
    data object GetImagesError : ImagesListStates
}

/**
 * MVVM => Model-View-ViewModel
 * MVP => Model-View-Presenter
 * MVI => Model-View-Intent
 */

sealed interface ImagesListIntents : BaseIntent {
    data object ResetIntent : ImagesListIntents;
    data object GetImages : ImagesListIntents;
}



