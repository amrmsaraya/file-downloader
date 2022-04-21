package com.github.amrmsaraya.nagwa.data.source

import androidx.annotation.RawRes
import com.github.amrmsaraya.nagwa.data.entity.FileDTO

interface LocalDataSource {
    suspend fun getFiles(@RawRes resourceId: Int): List<FileDTO>
}