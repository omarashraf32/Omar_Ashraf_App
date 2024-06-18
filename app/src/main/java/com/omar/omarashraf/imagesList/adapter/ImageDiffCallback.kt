package com.omar.omarashraf.imagesList.adapter

import androidx.recyclerview.widget.DiffUtil
import com.omar.domain.model.CategoryResponseItem

internal class ImageDiffCallback : DiffUtil.ItemCallback<CategoryResponseItem>() {
    override fun areItemsTheSame(
        oldItem: CategoryResponseItem,
        newItem: CategoryResponseItem
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: CategoryResponseItem,
        newItem: CategoryResponseItem
    ): Boolean {
        return oldItem == newItem
    }

}
