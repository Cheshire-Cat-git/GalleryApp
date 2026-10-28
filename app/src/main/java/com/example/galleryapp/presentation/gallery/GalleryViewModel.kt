package com.example.galleryapp.presentation.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.galleryapp.domain.model.GalleryImage
import com.example.galleryapp.domain.usecase.GetRandomImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val getRandomImagesUseCase: GetRandomImagesUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(GalleryUiState())
    val uiState: StateFlow<GalleryUiState> = _uiState.asStateFlow()

    init {
        loadImages()
    }

    fun loadImages() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            getRandomImagesUseCase()
                .onSuccess { images ->
                    _uiState.update { it.copy(images = images, isLoading = false) }
                }
                .onFailure { exception ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = exception.localizedMessage ?: "Failed to load images"
                        )
                    }
                }
        }
    }

    fun onImageSelected(image: GalleryImage) {
        _uiState.update { it.copy(selectedImage = image) }
    }

    fun onDialogDismissed() {
        _uiState.update { it.copy(selectedImage = null) }
    }
}
