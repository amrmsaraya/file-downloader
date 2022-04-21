package com.github.amrmsaraya.nagwa.data.sourceImpl

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.github.amrmsaraya.nagwa.R
import com.github.amrmsaraya.nagwa.data.entity.FileDTO
import com.github.amrmsaraya.nagwa.data.source.LocalDataSource
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class, ExperimentalSerializationApi::class)
@RunWith(AndroidJUnit4::class)
class LocalDataSourceImplTest {
    private lateinit var localDataSource: LocalDataSource
    private lateinit var context: Context

    @Before
    fun setUp() {
        context = InstrumentationRegistry.getInstrumentation().targetContext
        localDataSource = LocalDataSourceImpl(context)
    }

    @Test
    fun getFiles() = runTest {
        // Given
        val resourceId = R.raw.files_response
        val files =
            Json.decodeFromStream<List<FileDTO>>(context.resources.openRawResource(resourceId))

        // When
        val result = localDataSource.getFiles(resourceId)

        // Then
        Truth.assertThat(result).isEqualTo(files)
    }

}