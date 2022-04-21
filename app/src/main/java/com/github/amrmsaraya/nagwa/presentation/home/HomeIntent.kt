package com.github.amrmsaraya.nagwa.presentation.home

import androidx.annotation.RawRes
import com.github.amrmsaraya.nagwa.domain.entity.File

sealed class HomeIntent {
    data class GetFiles(@RawRes val resourceId: Int) : HomeIntent()
    data class UpdateDownloadProgress(val file: File, val downloadProgress: Int) : HomeIntent()
}
