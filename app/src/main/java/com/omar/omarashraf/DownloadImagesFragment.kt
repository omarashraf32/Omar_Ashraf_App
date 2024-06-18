package com.omar.omarashraf

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.google.gson.Gson
import com.omar.domain.model.CategoryResponseItem
import com.omar.omarashraf.databinding.FragmentDownloadImagesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.http.Url
import java.net.URL

@AndroidEntryPoint
class DownloadImagesFragment : Fragment() {

    private lateinit var binding: FragmentDownloadImagesBinding
    private val args: DownloadImagesFragmentArgs by navArgs()


    private val images: CategoryResponseItem? by lazy {
        try {
            Gson().fromJson(args.urlImage, CategoryResponseItem::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDownloadImagesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (images == null) {//in case of images was null -> return
            Toast.makeText(context, R.string.something_went_wrong, Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
            return
        }
        setupViews()
        downloadActionButton()
    }


    private fun downloadActionButton() {
        val imageUrl = images?.downloadUrl
        binding.bntDownload.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val url = URL(imageUrl)
                    val connection = url.openConnection()
                    connection.connect()

                    val inputStream = connection.getInputStream()
                    val bitmap = BitmapFactory.decodeStream(inputStream)

                    withContext(Dispatchers.Main) {
                        binding.imgDownload.setImageBitmap(bitmap)
                        saveImageToGallery(bitmap)
                        Toast.makeText(context, R.string.image_downloaded, Toast.LENGTH_SHORT)
                            .show()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }
    }

    private fun saveImageToGallery(bitmap: Bitmap?) {
        val filename = "${System.currentTimeMillis()}.jpg"
        val saveImage = MediaStore.Images.Media.insertImage(requireContext().contentResolver, bitmap, filename, "Image downloaded from app")
        val uri = Uri.parse(saveImage)
        Toast.makeText(context, "Image saved to gallery", Toast.LENGTH_SHORT).show()
    }

    private fun setupViews() {
        binding.apply {
            images.apply {
                imgDownload.load(images)
            }
        }
    }
}