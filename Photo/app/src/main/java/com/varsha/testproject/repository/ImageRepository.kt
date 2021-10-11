package com.varsha.testproject.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.varsha.testproject.api.ImageService
import com.varsha.testproject.pagingSource.ImagePagingSource

class ImageRepository (private val imageService: ImageService) {
    fun getPhotosId()=
        Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ImagePagingSource(imageService) }
        ).liveData
}