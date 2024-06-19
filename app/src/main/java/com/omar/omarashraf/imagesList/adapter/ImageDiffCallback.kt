package com.omar.omarashraf.imagesList.adapter

import androidx.recyclerview.widget.DiffUtil
import com.omar.domain.model.ImageModel

internal class ImageDiffCallback : DiffUtil.ItemCallback<ImageModel>() {
    override fun areItemsTheSame(
        oldItem: ImageModel,
        newItem: ImageModel
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: ImageModel,
        newItem: ImageModel
    ): Boolean {
        return oldItem == newItem
    }

}
