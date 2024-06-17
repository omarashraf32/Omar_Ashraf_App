package com.omar.omarashraf.di

import com.omar.data.remote.ApiService
import com.omar.data.repo.ImageRepoImpl
import com.omar.domain.repo.ImageRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    fun provideRepo(apiService: ApiService): ImageRepo{
        return ImageRepoImpl(apiService)
    }
}