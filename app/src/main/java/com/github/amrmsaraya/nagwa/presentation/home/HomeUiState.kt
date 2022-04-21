package com.github.amrmsaraya.nagwa.presentation.home

import com.github.amrmsaraya.nagwa.domain.entity.File

data class HomeUiState(
    val files: List<File> = emptyList(),
    val isDownloading :Boolean = false,
)
