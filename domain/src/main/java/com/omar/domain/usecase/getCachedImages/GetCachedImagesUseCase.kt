package com.omar.domain.usecase.getCachedImages

import com.omar.domain.repo.ImageRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetCachedImagesUseCase @Inject constructor(val imageRepo: ImageRepo) {
    fun execute(): Flow<GetDownloadedImagesResult> =
        flow {
            try {
                val images = imageRepo.getCachedImages()
                emit(GetDownloadedImagesResult.Success(images))
            } catch (e: Exception) {
                e.printStackTrace()
                emit(GetDownloadedImagesResult.Error)
            }
        }

}