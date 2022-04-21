package com.github.amrmsaraya.nagwa.presentation.home

import androidx.annotation.RawRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.amrmsaraya.nagwa.R
import com.github.amrmsaraya.nagwa.di.MainDispatcher
import com.github.amrmsaraya.nagwa.domain.entity.File
import com.github.amrmsaraya.nagwa.domain.usecase.GetFilesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getFilesUseCase: GetFilesUseCase,
    @MainDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val intentChannel = Channel<HomeIntent>()

    var uiState by mutableStateOf(HomeUiState())
        private set

    init {
        handleIntent()
        getFiles(R.raw.files_response)
    }

    fun sendIntent(intent: HomeIntent) = viewModelScope.launch(dispatcher) {
        intentChannel.send(intent)
    }

    private fun handleIntent() = viewModelScope.launch(dispatcher) {
        intentChannel.consumeAsFlow().collect {
            when (it) {
                is HomeIntent.GetFiles -> getFiles(it.resourceId)
                is HomeIntent.UpdateDownloadProgress -> updateDownloadProgress(
                    it.file,
                    it.downloadProgress
                )
            }
        }
    }

    private fun getFiles(@RawRes resourceId: Int) = viewModelScope.launch(dispatcher) {
        val files = getFilesUseCase(resourceId)
        uiState = uiState.copy(files = files)
    }

    private fun updateDownloadProgress(file: File, downloadProgress: Int) =
        viewModelScope.launch(dispatcher) {
            uiState = uiState.copy(
                files = uiState.files.map {
                    if (it.id == file.id) {
                        it.copy(
                            isDownloading = downloadProgress in 0..99,
                            downloadProgress = downloadProgress
                        )
                    } else {
                        it
                    }
                },
                isDownloading = downloadProgress in 0..99
            )
        }
}