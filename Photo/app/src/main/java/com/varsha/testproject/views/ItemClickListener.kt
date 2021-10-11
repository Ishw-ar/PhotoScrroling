package com.varsha.testproject.views

import com.varsha.testproject.models.Photo

interface ItemClickListener {
    fun onItemClicked(photo: Photo)
}