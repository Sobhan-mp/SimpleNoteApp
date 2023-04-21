package com.sobhanmp.domain.repository

import com.sobhanmp.domain.model.NoteModel
import com.sobhanmp.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun saveNewNote(note: NoteModel): Flow<Resource<Unit>>

    fun updateNote(note: NoteModel): Flow<Resource<Unit>>

    fun deleteNote(note: NoteModel): Flow<Resource<Unit>>

    fun getNotesList(): Flow<Resource<NoteModel>>
}