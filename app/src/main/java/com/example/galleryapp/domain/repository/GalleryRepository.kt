package com.example.galleryapp.domain.repository

import com.example.galleryapp.domain.model.GalleryImage

interface GalleryRepository {
    suspend fun getRandomImages(): Result<List<GalleryImage>>
}
