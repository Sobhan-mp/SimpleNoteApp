package com.sobhanmp.data.repository

import com.sobhanmp.domain.model.NoteModel
import com.sobhanmp.domain.repository.NoteRepository
import com.sobhanmp.domain.util.Resource
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(): NoteRepository {

    override fun saveNewNote(note: NoteModel): Flow<Resource<Unit>> {
        TODO("Not yet implemented")
    }

    override fun updateNote(note: NoteModel): Flow<Resource<Unit>> {
        TODO("Not yet implemented")
    }

    override fun deleteNote(note: NoteModel): Flow<Resource<Unit>> {
        TODO("Not yet implemented")
    }

    override fun getNotesList(): Flow<Resource<NoteModel>> {
        TODO("Not yet implemented")
    }
}