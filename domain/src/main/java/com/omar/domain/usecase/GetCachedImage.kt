package com.omar.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCachedImageUseCase @Inject constructor() {
    fun execute(): Flow<GetImagesStates> =
        flow {
            emit(Success(emptyList()))
        }

}