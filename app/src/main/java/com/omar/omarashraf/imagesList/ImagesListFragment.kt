package com.omar.omarashraf.imagesList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.omar.domain.model.ImageModel
import com.omar.omarashraf.R
import com.omar.omarashraf.databinding.FragmentImagesListBinding
import com.omar.omarashraf.imagesList.adapter.ImageAdapter
import com.omar.omarashraf.imagesList.states.ImagesListIntents
import com.omar.omarashraf.imagesList.states.ImagesListStates
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ImagesListFragment : Fragment(), ImageAdapter.OnItemClickListener {

    private lateinit var binding: FragmentImagesListBinding
    private val imageAdapter = ImageAdapter(this)
    private lateinit var viewModel: ImageListViewModel
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImagesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        setupRecyclerView()
        setupLiveDataObservers()
        fetchData()
        initNavDrawer()

    }

    private fun initNavDrawer() {
       toggle = ActionBarDrawerToggle(requireActivity(),binding.drawerLayout,R.string.open,R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navViewAction()


    }

    private fun navViewAction() {
        binding.navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_download ->{
                    findNavController().navigate(
                        ImagesListFragmentDirections.actionImagesListFragmentToDownloadImagesFragment()
                    )
                }
            }
        }
    }

    private fun fetchData() = viewModel.addAction(ImagesListIntents.GetImages)

    private fun setupLiveDataObservers() {
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    is ImagesListStates.Loading -> showLoading()
                    is ImagesListStates.GetImagesSuccess -> onGetImagesSuccess(state.images)
                    is ImagesListStates.GetImagesError -> onGetImagesError()
                    is ImagesListStates.InitState -> onGetImagesInitState()
                }
            }
        }
    }

    private fun onGetImagesInitState() {}

    private fun onGetImagesError() {
        hideLoading()
        view?.let { view ->
            Snackbar.make(view, R.string.something_went_wrong, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.retry)
                { retryGetImages() }
                .show()
        }
    }

    private fun retryGetImages() {
        viewModel.addAction(ImagesListIntents.ResetIntent)
        fetchData()
    }

    private fun onGetImagesSuccess(images: List<ImageModel>) {
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

    override fun onItemClickListener(category: ImageModel) {
        findNavController().navigate(
            ImagesListFragmentDirections.actionImagesListFragmentToDownloadImagesFragment()
        )
    }
}