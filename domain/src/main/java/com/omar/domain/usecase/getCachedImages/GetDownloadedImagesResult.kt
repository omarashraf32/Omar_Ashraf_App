package com.omar.domain.usecase.getCachedImages

import java.io.File

sealed class GetDownloadedImagesResult {
    class Success(val images: List<File>) : GetDownloadedImagesResult()
    data object Error : GetDownloadedImagesResult()
}