package com.github.amrmsaraya.nagwa.data.entity


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FileDTO(
    @SerialName("id")
    val id: Int = 0,
    @SerialName("type")
    val type: String = "",
    @SerialName("url")
    val url: String = "",
    @SerialName("name")
    val name: String = ""
)