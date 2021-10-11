package com.varsha.testproject.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.varsha.testproject.models.Photo

@BindingAdapter("imageFromUrl")
fun ImageView.imageFromUrl(photo: Photo){
    Glide.with(this.context).load("https://live.staticflickr.com/" + photo.server+ "/" + photo.id+ "_" + photo.secret+ ".jpg").into(this)
}