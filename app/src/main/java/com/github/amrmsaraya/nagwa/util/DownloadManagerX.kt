package com.github.amrmsaraya.nagwa.util

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Environment
import androidx.activity.ComponentActivity
import com.github.amrmsaraya.nagwa.domain.entity.File
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

fun Context.download(file: File): Long {
    val fileName = "${file.id}_${file.url.substringAfterLast("/")}"

    val request = DownloadManager.Request(Uri.parse(file.url)).apply {
        setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
        setDestinationInExternalFilesDir(this@download, Environment.DIRECTORY_DOWNLOADS, fileName)
        setTitle(fileName)
        setDescription("Downloading $fileName")
        setAllowedOverMetered(true)
        setAllowedOverRoaming(true)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            setRequiresCharging(false)
        }
    }

    val downloadManager = getSystemService(ComponentActivity.DOWNLOAD_SERVICE) as DownloadManager
    return downloadManager.enqueue(request)
}

@SuppressLint("Range")
suspend fun Context.getDownloadProgress(id: Long, onProgressChange: (Int) -> Unit) {
    val downloadManager = getSystemService(ComponentActivity.DOWNLOAD_SERVICE) as DownloadManager
    var status = DownloadManager.STATUS_PENDING
    while (status != DownloadManager.STATUS_FAILED && status != DownloadManager.STATUS_SUCCESSFUL) {
        val query = DownloadManager.Query().setFilterById(id)
        val cursor = downloadManager.query(query)
        if (cursor.moveToFirst()) {
            status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS))
            val downloaded =
                cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR))
            val total =
                cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES))
            onProgressChange(if (total > 0) ((downloaded * 100f) / total).roundToInt() else 0)
            delay(500)
        } else {
            onProgressChange(-1)
            return
        }
        cursor.close()
    }
    onProgressChange(-1)
}

fun File.getStatus(context: Context): DownloadStatus {
    val fileInStorage =
        context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)?.listFiles()
            ?.find { it.name == "${id}_${url.substringAfterLast("/")}" }

    return if (isDownloading) {
        DownloadStatus.IsDownloading(downloadProgress)
    } else {
        fileInStorage?.let { DownloadStatus.Downloaded } ?: DownloadStatus.NotDownloaded
    }
}

sealed class DownloadStatus {
    object NotDownloaded : DownloadStatus()
    data class IsDownloading(val progress: Int) : DownloadStatus()
    object Downloaded : DownloadStatus()
}