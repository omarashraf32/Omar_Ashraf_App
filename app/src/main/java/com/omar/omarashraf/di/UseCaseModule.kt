package com.omar.omarashraf.di

import com.omar.domain.repo.ImageRepo
import com.omar.domain.usecase.GetUpdateImagesUseCase
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    fun provideUseCase(imageRepo: ImageRepo): GetUpdateImagesUseCase{
        return GetUpdateImagesUseCase(imageRepo)
    }
}