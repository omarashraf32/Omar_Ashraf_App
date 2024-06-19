package com.omar.data.repo

import android.app.Application
import android.content.Context
import android.os.Environment
import com.omar.data.remote.ApiService
import com.omar.domain.model.ImageModel
import com.omar.domain.repo.ImageRepo
import okhttp3.ResponseBody
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject



class ImageRepoImpl @Inject constructor(private val apiService: ApiService, private val context: Application) : ImageRepo {

    override suspend fun getImagesList(): List<ImageModel> = apiService.getImagesList()

    override suspend fun getCachedImages(): List<File> {
        val images = getImagesList().take(3) // remove 3
        val paths: MutableList<String> = images.map { image ->
            if (image.downloadUrl.isNullOrEmpty()) return@map ""
            try {
                val path = downloadImage(image.downloadUrl!!)
                path
            } catch (e: Exception) {
                e.printStackTrace()
                ""
            }
        }.toMutableList()

        paths.removeAll { path -> path.isEmpty() }
        return paths.map { path -> File(path) }
    }

    private suspend fun downloadImage(downloadUrl: String): String {
        val response = apiService.downloadImage(imageUrl = downloadUrl)
        val path =
            "${context.cacheDir.path}/${System.currentTimeMillis()}.jpg"

        writeResponseBody(response, path)
        return path
    }

    fun writeResponseBody(body: ResponseBody, path: String): Boolean {
        try {
            val file = File(path)
            var inputStream: InputStream? = null
            var outputStream: OutputStream? = null
            try {
                val fileReader = ByteArray(4096)

                inputStream = body.byteStream()
                outputStream = FileOutputStream(file)

                while (true) {
                    val read = inputStream.read(fileReader)
                    if (read == -1) {
                        break
                    }
                    outputStream.write(fileReader, 0, read)
                }
                outputStream.flush()
                return true
            } catch (e: IOException) {
                return false
            } finally {
                inputStream?.close()
                outputStream?.close()
            }
        } catch (e: IOException) {
            return false
        }
    }


}