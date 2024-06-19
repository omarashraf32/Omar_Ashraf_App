package com.omar.domain.repo

import com.omar.domain.model.ImageModel
import java.io.File

interface ImageRepo {
    suspend fun getImagesList(): List<ImageModel>
    suspend fun getCachedImages(): List<File> // list<DownloadedFiles>
}