package com.example.galleryapp.data.remote

import com.example.galleryapp.domain.model.GalleryImage
import com.google.gson.annotations.SerializedName

data class PicsumDto(
    val id: String,
    val author: String,
    val width: Int,
    val height: Int,
    val url: String,
    @SerializedName("download_url") val downloadUrl: String
) {
    fun toDomain(): GalleryImage = GalleryImage(
        id = id,
        author = author,
        thumbnailUrl = "https://picsum.photos/id/$id/300/300",
        fullUrl = "https://picsum.photos/id/$id/$width/$height"
    )
}
