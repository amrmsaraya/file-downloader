package com.github.amrmsaraya.nagwa.data.mapper

import com.github.amrmsaraya.nagwa.data.entity.FileDTO
import com.github.amrmsaraya.nagwa.domain.entity.File
import com.google.common.truth.Truth
import org.junit.Test

class FileMapperTest {

    @Test
    fun toFile_withFileDTO_thenReturnFile() {
        // Given
        val fileDTO = FileDTO(
            id = 1,
            type = "type",
            url = "url",
            name = "name"
        )

        // When
        val result = fileDTO.toFile()

        // Then
        Truth.assertThat(result).isEqualTo(
            File(
                id = 1,
                type = "type",
                url = "url",
                name = "name"
            )
        )
    }

    @Test
    fun toFileDTO_withFile_thenReturnFileDTO() {
        // Given
        val file = File(
            id = 1,
            type = "type",
            url = "url",
            name = "name"
        )

        // When
        val result = file.toFileDTO()

        // Then
        Truth.assertThat(result).isEqualTo(
            FileDTO(
                id = 1,
                type = "type",
                url = "url",
                name = "name"
            )
        )
    }
}