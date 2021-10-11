package com.varsha.testproject.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.varsha.testproject.R
import com.varsha.testproject.databinding.ItemLayoutBinding
import com.varsha.testproject.models.Photo
import com.varsha.testproject.views.ItemClickListener

/**
 * This class is responsible for create and bind the data in the recyclerview
 */
class ImageAdapter(val listener: ItemClickListener)
    : PagingDataAdapter<Photo, ImageAdapter.ImageViewHolder>(PHOTO_COMPARATOR) {

    class ImageViewHolder(private val itemLayoutBinding: ItemLayoutBinding, private val itemClickListener: ItemClickListener)
        : RecyclerView.ViewHolder(itemLayoutBinding.root){
        fun onBind(photo: Photo){
            itemLayoutBinding.itemClickListener = itemClickListener
            itemLayoutBinding.photo=photo
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        val itemLayoutBinding: ItemLayoutBinding= DataBindingUtil.inflate(layoutInflater,
            R.layout.item_layout, parent, false)
        return ImageViewHolder(itemLayoutBinding,listener)

    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {

        val currentPhoto = getItem(position)
        if (currentPhoto != null) {
            holder.onBind(currentPhoto)
        }

    }
    companion object {
        private val PHOTO_COMPARATOR = object : DiffUtil.ItemCallback<Photo>() {
            override fun areItemsTheSame(oldItem: Photo, newItem: Photo) =
                oldItem.secret == newItem.secret
            override fun areContentsTheSame(oldItem: Photo, newItem: Photo) =
                oldItem == newItem
        }
    }

}