package com.example.galleryapp.data.repository

import com.example.galleryapp.data.remote.ApiService
import com.example.galleryapp.domain.model.GalleryImage
import com.example.galleryapp.domain.repository.GalleryRepository
import javax.inject.Inject

class GalleryRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : GalleryRepository {

    override suspend fun getRandomImages(): Result<List<GalleryImage>> {
        return runCatching {
            var randomPage = (1..50).random()
            var dtos = apiService.getImages(page = randomPage, limit = 15)

            if (dtos.isEmpty()) {
                randomPage = (1..10).random()
                dtos = apiService.getImages(page = randomPage, limit = 15)
            }

            check(dtos.isNotEmpty()) { "No images available from server" }
            dtos.map { it.toDomain() }
        }
    }
}
