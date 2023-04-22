package com.sobhanmp.data.mapper

import com.sobhanmp.data.local.entity.NoteEntity
import com.sobhanmp.domain.model.NoteModel

fun NoteEntity.toNoteModel(): NoteModel {
    return NoteModel(id, title, text, date)
}

fun NoteModel.toNoteEntity(id: Int? = null): NoteEntity {
    return NoteEntity(id = id, title = title, text = text, date = date)
}