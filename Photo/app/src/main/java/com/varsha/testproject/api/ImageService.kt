package com.varsha.testproject.api

import com.varsha.testproject.models.ImageResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageService {
    @GET("services/rest/")
    suspend fun getPhotosId(
        @Query("method") method:String,
        @Query("api_key") apiKey:String,
        @Query("format") format:String,
        @Query("nojsoncallback") nojsoncallback:Int,
        @Query("safe_search") safeSearch:Int,
        @Query("tags") tags:String,
        @Query("per_page") perPage:Int,
        @Query("page") page:Int
    ): ImageResponse
}