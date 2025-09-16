package com.assignment.therapist_dictonary.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Therapist(
    @SerialName("id")
    val id: String? = null,

    @SerialName("name")
    val name: String,

    @SerialName("specialization")
    val specialization: String,

    @SerialName("availability")
    val availability: Boolean,

    @SerialName("about")
    val about: String,

    @SerialName("languages")
    val languages:List<String>? = emptyList()

)
