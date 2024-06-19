package com.omar.omarashraf.downloadedImages

import com.omar.omarashraf.base.BaseIntent
import com.omar.omarashraf.base.BaseState
import java.io.File

sealed interface DownloadedImagesStates : BaseState {
    data object Loading : DownloadedImagesStates
    class SuccessImages(val images: List<File>) : DownloadedImagesStates
    data object Error : DownloadedImagesStates
}

sealed interface DownloadedImagesIntents : BaseIntent {
    data object GetDownloadedImages : DownloadedImagesIntents
}