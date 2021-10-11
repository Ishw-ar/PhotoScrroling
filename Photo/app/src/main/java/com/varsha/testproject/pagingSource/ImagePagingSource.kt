package com.varsha.testproject.pagingSource

import androidx.paging.PagingSource
import com.varsha.testproject.api.ImageService
import com.varsha.testproject.models.Photo
import retrofit2.HttpException
import java.io.IOException

class ImagePagingSource(private val imageService: ImageService) :
    PagingSource<Int, Photo>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        //for first case it will be null, then we can pass some default value, in our case it's 1
        val page = params.key ?: 1
        return try {
            val response = imageService.getPhotosId(
                "flickr.photos.search",
                "3e7cc266ae2b0e0d78e279ce8e361736",
                "json",
                1,
                1,
                "kitten",
                params.loadSize,
                page
            )
            val photos = response.photos.photo
            LoadResult.Page(
                data = photos, prevKey = if (page == 1) null else page - 1,
                nextKey = if (photos.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }}