package com.github.amrmsaraya.nagwa.presentation.home

import com.github.amrmsaraya.nagwa.domain.entity.File
import com.github.amrmsaraya.nagwa.domain.usecase.GetFilesUseCase
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {
    private lateinit var getFilesUseCase: GetFilesUseCase
    private lateinit var homeViewModel: HomeViewModel
    private val dispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        getFilesUseCase = mockk(relaxed = true)
        homeViewModel = HomeViewModel(
            getFilesUseCase = getFilesUseCase,
            dispatcher = dispatcher
        )
    }

    @Test
    fun sendIntent_withGetFilesIntent_thenUiStateShouldBeUpdated() = runTest {
        // Given
        val files = listOf(File(id = 1), File(id = 2), File(id = 3))
        coEvery { getFilesUseCase(1) } returns files

        // When
        homeViewModel.sendIntent(HomeIntent.GetFiles(1))

        // Then
        Truth.assertThat(homeViewModel.uiState.files).isEqualTo(files)
    }

    @Test
    fun sendIntent_withUpdateDownloadProgressIntent_thenUiStateShouldBeUpdated() = runTest {
        // Given
        val files = listOf(File(id = 1), File(id = 2), File(id = 3))
        coEvery { getFilesUseCase(1) } returns files
        homeViewModel.sendIntent(HomeIntent.GetFiles(1))

        // When
        homeViewModel.sendIntent(HomeIntent.UpdateDownloadProgress(File(id = 1), 15))

        // Then
        Truth.assertThat(homeViewModel.uiState.files)
            .contains(File(id = 1, isDownloading = true, downloadProgress = 15))
    }

    @Test
    fun sendIntent_withUpdateDownloadProgressIntentAndProgressMinusOne_thenUiStateShouldBeUpdated() = runTest {
        // Given
        val files = listOf(File(id = 1), File(id = 2), File(id = 3))
        coEvery { getFilesUseCase(1) } returns files
        homeViewModel.sendIntent(HomeIntent.GetFiles(1))

        // When
        homeViewModel.sendIntent(HomeIntent.UpdateDownloadProgress(File(id = 1), 15))
        homeViewModel.sendIntent(HomeIntent.UpdateDownloadProgress(File(id = 1), -1))

        // Then
        Truth.assertThat(homeViewModel.uiState.files)
            .contains(File(id = 1, isDownloading = false, downloadProgress = -1))
    }
}