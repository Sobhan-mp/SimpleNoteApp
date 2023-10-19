package com.sobhanmp.domain.model

import java.io.Serializable

data class NoteModel(
    val id: Int? = null,
    val title: String,
    val text: String,
    val date: String,
    val backgroundColor: Int? = null
): Serializable
