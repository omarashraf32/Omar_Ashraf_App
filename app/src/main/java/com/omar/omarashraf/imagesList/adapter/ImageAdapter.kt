package com.omar.omarashraf.imagesList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.omar.domain.model.CategoryResponseItem
import com.omar.omarashraf.databinding.ItemImagesBinding

class ImageAdapter(private val onItemClicked: OnItemClickListener) :
    ListAdapter<CategoryResponseItem, ImageAdapter.ViewHolder>(ImageDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            ItemImagesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder
        (private val itemBinding: ItemImagesBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(category: CategoryResponseItem) {
            category.apply {
                itemBinding.apply {
                    imgImage.load(downloadUrl)
                }
            }
        }

        init {
            itemView.setOnClickListener {
                onItemClicked.onItemClickListener(getItem(adapterPosition))
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClickListener(category: CategoryResponseItem)
    }

}