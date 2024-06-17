package com.omar.omarashraf

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omar.domain.usecase.GetImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageViewModel
@Inject constructor(private val getUpdateImage: GetImagesUseCase) : ViewModel() {

    fun getImage(){
        viewModelScope.launch {
            try {
                getUpdateImage()
            }catch (e: Exception){
                Log.e("ImageViewModel",e.message.toString() )
            }

        }
    }
}