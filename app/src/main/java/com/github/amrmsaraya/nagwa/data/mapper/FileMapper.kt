package com.github.amrmsaraya.nagwa.data.mapper

import com.github.amrmsaraya.nagwa.data.entity.FileDTO
import com.github.amrmsaraya.nagwa.domain.entity.File

fun FileDTO.toFile() = File(
    id = id,
    type = type,
    url = url,
    name = name
)

fun File.toFileDTO() = FileDTO(
    id = id,
    type = type,
    url = url,
    name = name
)