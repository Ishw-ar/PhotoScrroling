package com.varsha.testproject.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.varsha.testproject.models.Photo
import com.varsha.testproject.repository.ImageRepository

class ImageViewModel(private val repository: ImageRepository) : ViewModel() {
    fun fetchImagesLiveData(): LiveData<PagingData<Photo>> {
        return repository.getPhotosId()
            .map { data -> data.map { it } }
            .cachedIn(viewModelScope)
    }
}