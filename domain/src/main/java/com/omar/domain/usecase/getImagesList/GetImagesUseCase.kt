package com.omar.domain.usecase.getImagesList

import com.omar.domain.repo.ImageRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetImagesUseCase @Inject constructor(private val imageRepo: ImageRepo) {
    suspend fun execute(): Flow<GetImagesResult> =
        flow {
            emit(
                try {
                    val images = imageRepo.getImagesList()
                    GetImagesResult.Success(image = images)
                } catch (e: Exception) {
                    e.printStackTrace()
                    GetImagesResult.Error
                }
            )
        }
}