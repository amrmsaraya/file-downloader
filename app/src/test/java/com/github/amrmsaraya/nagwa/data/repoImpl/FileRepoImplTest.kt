package com.github.amrmsaraya.nagwa.data.repoImpl

import com.github.amrmsaraya.nagwa.data.entity.FileDTO
import com.github.amrmsaraya.nagwa.data.mapper.toFile
import com.github.amrmsaraya.nagwa.data.source.LocalDataSource
import com.github.amrmsaraya.nagwa.domain.repository.FileRepo
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class FileRepoImplTest {
    private lateinit var localDataSource: LocalDataSource
    private lateinit var fileRepo: FileRepo

    private val dispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        localDataSource = mockk(relaxed = true)
        fileRepo = FileRepoImpl(
            localDataSource = localDataSource,
            dispatcher = dispatcher
        )
    }

    @Test
    fun getFiles_withResourceId_returnsListOfFiles() = runTest {
        // Given
        val file1 = FileDTO(id = 1)
        val file2 = FileDTO(id = 2)
        val file3 = FileDTO(id = 3)
        val files = listOf(file1, file2, file3)
        coEvery { localDataSource.getFiles(1) } returns files

        // When
        val result = fileRepo.getFiles(1)

        // Then
        Truth.assertThat(result).isEqualTo(files.map { it.toFile() })
    }
}