package com.omar.omarashraf.imagesList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.omar.domain.model.CategoryResponseItem
import com.omar.omarashraf.ImageListViewModel
import com.omar.omarashraf.R
import com.omar.omarashraf.databinding.FragmentImagesListBinding
import com.omar.omarashraf.di.states.GetImagesError
import com.omar.omarashraf.di.states.GetImagesLoading
import com.omar.omarashraf.di.states.GetImagesSuccess
import com.omar.omarashraf.di.states.InitState
import com.omar.omarashraf.imagesList.adapter.ImageAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ImagesListFragment : Fragment(), ImageAdapter.OnItemClickListener {

    private lateinit var binding: FragmentImagesListBinding
    private val imageAdapter = ImageAdapter(this)
    private lateinit var viewModel: ImageListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentImagesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        setupRecyclerView()
        setupLiveDataObservers()
        fetchData()

    }

    private fun fetchData() = viewModel.getImages()

    private fun setupLiveDataObservers() {
        lifecycleScope.launch {
            viewModel.categories.collect { state ->
                when (state) {
                    is GetImagesLoading -> showLoading()
                    is GetImagesSuccess -> onGetImagesSuccess(state.images)
                    is GetImagesError -> onGetImagesError()
                    is InitState -> onGetImagesInitState()
                    else -> {}
                }
            }
        }
    }

    private fun onGetImagesInitState() {}

    private fun onGetImagesError() {
        hideLoading()
        view?.let { view ->
            Snackbar.make(view, R.string.something_went_wrong, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.retry) { viewModel.getUpdateImages() }.show()
        }
    }

    private fun onGetImagesSuccess(images: List<CategoryResponseItem>) {
        hideLoading()
        imageAdapter.submitList(images)
    }

    private fun hideLoading() {

    }

    private fun showLoading() {

    }

    private fun setupRecyclerView() {
        binding.imagesRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = imageAdapter
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[ImageListViewModel::class.java]
    }

    override fun onItemClickListener(category: CategoryResponseItem) {
        findNavController().navigate(
            ImagesListFragmentDirections.actionImagesListFragmentToDownloadImagesFragment(
                Gson().toJson(
                    category
                )
            )
        )
    }
}