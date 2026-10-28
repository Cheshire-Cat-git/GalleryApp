package com.example.galleryapp.presentation.gallery

import com.example.galleryapp.domain.model.GalleryImage

data class GalleryUiState(
    val images: List<GalleryImage> = emptyList(),
    val isLoading: Boolean = false,
    val selectedImage: GalleryImage? = null,
    val error: String? = null
)
