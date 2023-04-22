package com.sobhanmp.domain.repository

import com.sobhanmp.domain.model.NoteModel
import com.sobhanmp.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    suspend fun saveNewNote(note: NoteModel): Flow<Resource<Unit>>

    suspend fun updateNote(note: NoteModel): Flow<Resource<Unit>>

    suspend fun deleteNote(note: NoteModel): Flow<Resource<Unit>>

    suspend fun getNotesList(): Flow<Resource<List<NoteModel>>>
}