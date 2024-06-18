package com.omar.omarashraf

import android.os.Bundle
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
        if(images == null){//in case of images was null -> return
            Toast.makeText(context, R.string.something_went_wrong, Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
            return
        }
        setupViews()
    }

    private fun setupViews() {
       binding.apply {
           images.apply {
               imgDownload.load(images)
           }
       }
    }
}