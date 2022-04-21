package com.github.amrmsaraya.nagwa.domain.entity


data class File(
    val id: Int = 0,
    val type: String = "",
    val url: String = "",
    val name: String = "",
    val isDownloading: Boolean = false,
    val downloadProgress: Int = 0
)