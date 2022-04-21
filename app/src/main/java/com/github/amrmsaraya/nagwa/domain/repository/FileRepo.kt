package com.github.amrmsaraya.nagwa.domain.repository

import androidx.annotation.RawRes
import com.github.amrmsaraya.nagwa.domain.entity.File

interface FileRepo {
    suspend fun getFiles(@RawRes resourceId: Int): List<File>
}