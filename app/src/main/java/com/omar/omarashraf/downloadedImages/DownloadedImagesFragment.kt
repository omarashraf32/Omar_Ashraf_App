package com.omar.omarashraf.downloadedImages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.omar.omarashraf.databinding.FragmentDownloadedImagesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.io.File

@AndroidEntryPoint
class DownloadedImagesFragment : Fragment() {


    private lateinit var binding: FragmentDownloadedImagesBinding
    private lateinit var viewModel: DownloadedImagesViewModel
    private val adapter = DownloadedImagesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDownloadedImagesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        setupViews()
        setupLiveDataObservers()
        fetchData()
    }

    private fun setupViews() {
        binding.imagesRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@DownloadedImagesFragment.adapter
        }
    }

    private fun setupLiveDataObservers() {
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    is DownloadedImagesStates.Loading -> showLoading()
                    is DownloadedImagesStates.SuccessImages -> onGetImagesSuccess(state.images)
                    is DownloadedImagesStates.Error -> onGetImagesError()
                }
            }
        }
    }

    private fun onGetImagesError() {

    }

    private fun onGetImagesSuccess(images: List<File>) {
        adapter.setItems(images)
    }

    private fun showLoading() {
        TODO("Not yet implemented")
    }

    private fun fetchData() {
        viewModel.addAction(DownloadedImagesIntents.GetDownloadedImages)
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[DownloadedImagesViewModel::class.java]
    }
}