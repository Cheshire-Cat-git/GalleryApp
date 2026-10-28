package com.example.galleryapp.domain.usecase

import com.example.galleryapp.domain.model.GalleryImage
import com.example.galleryapp.domain.repository.GalleryRepository
import javax.inject.Inject

class GetRandomImagesUseCase @Inject constructor(
    private val repository: GalleryRepository
) {
    suspend operator fun invoke(): Result<List<GalleryImage>> {
        return repository.getRandomImages()
    }
}
