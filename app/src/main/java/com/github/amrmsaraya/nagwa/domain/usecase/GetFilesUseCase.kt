package com.github.amrmsaraya.nagwa.domain.usecase

import androidx.annotation.RawRes
import com.github.amrmsaraya.nagwa.domain.entity.File
import com.github.amrmsaraya.nagwa.domain.repository.FileRepo
import javax.inject.Inject

class GetFilesUseCase @Inject constructor(private val fileRepo: FileRepo) {
    suspend operator fun invoke(@RawRes resourceId: Int): List<File> {
        return fileRepo.getFiles(resourceId)
    }
}