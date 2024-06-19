package com.omar.omarashraf.downloadedImages

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.omar.omarashraf.databinding.ItemImagesBinding
import java.io.File

class DownloadedImagesAdapter : RecyclerView.Adapter<DownloadedImagesAdapter.ViewHolder>() {

    private val items = ArrayList<File>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            ItemImagesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = items.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setItems(items: List<File>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    class ViewHolder(private val itemBinding: ItemImagesBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(file: File) {
            itemBinding.imgImage.load(file)
        }
    }
}