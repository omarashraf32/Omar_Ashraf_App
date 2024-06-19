package com.omar.omarashraf.di

import com.omar.data.remote.ApiService
import com.omar.data.repo.ImageRepoImpl
import com.omar.domain.repo.ImageRepo
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

//    @Provides
//    fun provideRepo(apiService: ApiService): ImageRepo{
//        return ImageRepoImpl(apiService)
//    }

    @Binds
    abstract fun bindImageRepo(repo: ImageRepoImpl): ImageRepo
}