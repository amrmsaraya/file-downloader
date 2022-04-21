package com.github.amrmsaraya.nagwa.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Download
import androidx.compose.material.icons.rounded.DownloadDone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.amrmsaraya.nagwa.util.DownloadStatus
import com.github.amrmsaraya.nagwa.util.download
import com.github.amrmsaraya.nagwa.util.getDownloadProgress
import com.github.amrmsaraya.nagwa.util.getStatus
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    if (uiState.isLoading) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            modifier = modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(uiState.files) { file ->
                FileCard(name = file.name, status = file.getStatus(context)) {
                    if (!uiState.isDownloading) {
                        val id = context.download(file)
                        scope.launch {
                            context.getDownloadProgress(id) {
                                viewModel.sendIntent(HomeIntent.UpdateDownloadProgress(file, it))
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FileCard(name: String, status: DownloadStatus, onClick: () -> Unit) {
    Surface(
        tonalElevation = 2.dp, shape = MaterialTheme.shapes.medium,
        onClick = { if (status is DownloadStatus.NotDownloaded) onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = name
            )
            Spacer(modifier = Modifier.size(8.dp))
            when (status) {
                DownloadStatus.Downloaded -> {
                    Icon(
                        modifier = Modifier.size(32.dp),
                        imageVector = Icons.Rounded.DownloadDone,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                is DownloadStatus.IsDownloading -> {
                    Box(contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(
                            progress = 1f,
                            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.25f),
                            strokeWidth = 2.dp
                        )
                        CircularProgressIndicator(
                            progress = status.progress / 100f,
                            strokeWidth = 2.dp
                        )
                        Text(
                            text = "${status.progress}%",
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
                DownloadStatus.NotDownloaded -> {
                    Icon(
                        modifier = Modifier.size(32.dp),
                        imageVector = Icons.Rounded.Download,
                        contentDescription = null,
                    )
                }
            }
        }
    }
}
