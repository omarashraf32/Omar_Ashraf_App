package com.omar.domain.usecase

import com.omar.domain.repo.ImageRepo

class GetImagesUseCase(private val imageRepo: ImageRepo ) {
    suspend operator fun invoke() = imageRepo.getImageFromRemote()
}