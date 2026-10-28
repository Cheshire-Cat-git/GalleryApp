package com.example.galleryapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/v2/list")
    suspend fun getImages(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): List<PicsumDto>
}
