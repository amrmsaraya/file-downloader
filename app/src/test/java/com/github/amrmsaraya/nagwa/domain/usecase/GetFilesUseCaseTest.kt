package com.github.amrmsaraya.nagwa.domain.usecase

import com.github.amrmsaraya.nagwa.domain.entity.File
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
class GetFilesUseCaseTest {
    private lateinit var fileRepo: FileRepo
    private lateinit var getFilesUseCase: GetFilesUseCase

    @Before
    fun setUp() {
        fileRepo = mockk(relaxed = true)
        getFilesUseCase = GetFilesUseCase(fileRepo)
    }

    @Test
    fun getFiles_withResourceId_returnsListOfFiles() = runTest {
        // Given
        val file1 = File(id = 1)
        val file2 = File(id = 2)
        val file3 = File(id = 3)
        val files = listOf(file1, file2, file3)
        coEvery { fileRepo.getFiles(1) } returns files

        // When
        val result = getFilesUseCase(1)

        // Then
        Truth.assertThat(result).isEqualTo(files)
    }
}