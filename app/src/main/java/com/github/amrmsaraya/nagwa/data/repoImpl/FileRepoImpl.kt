package com.github.amrmsaraya.nagwa.data.repoImpl

import com.github.amrmsaraya.nagwa.data.mapper.toFile
import com.github.amrmsaraya.nagwa.data.source.LocalDataSource
import com.github.amrmsaraya.nagwa.di.IoDispatcher
import com.github.amrmsaraya.nagwa.domain.entity.File
import com.github.amrmsaraya.nagwa.domain.repository.FileRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class FileRepoImpl(
    private val localDataSource: LocalDataSource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : FileRepo {
    override suspend fun getFiles(resourceId: Int): List<File> {
        return withContext(dispatcher) {
            localDataSource.getFiles(resourceId).map { it.toFile() }
        }
    }
}