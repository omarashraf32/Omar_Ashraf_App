package com.omar.domain.usecase

import com.omar.domain.repo.ImageRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUpdateImagesUseCase @Inject constructor(private val imageRepo: ImageRepo ) {
suspend fun execute(): Flow<GetImagesStates> =
    flow {
        emit(
            try {
                val image = imageRepo.getImageFromRemote()
                Success(image = image)
            } catch (e: Exception) {
                e.printStackTrace()
                Error
            }
        )
    }
    }