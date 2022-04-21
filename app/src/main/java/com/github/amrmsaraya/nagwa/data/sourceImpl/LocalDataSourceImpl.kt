package com.github.amrmsaraya.nagwa.data.sourceImpl

import android.content.Context
import com.github.amrmsaraya.nagwa.data.entity.FileDTO
import com.github.amrmsaraya.nagwa.data.source.LocalDataSource
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream

class LocalDataSourceImpl(private val context: Context) : LocalDataSource {
    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun getFiles(resourceId: Int): List<FileDTO> {
        val json = Json { ignoreUnknownKeys = true }
        return json.decodeFromStream(context.resources.openRawResource(resourceId))
    }
}