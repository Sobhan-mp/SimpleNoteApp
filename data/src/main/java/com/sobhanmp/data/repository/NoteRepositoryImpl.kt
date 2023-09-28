package com.sobhanmp.data.repository

import com.sobhanmp.data.local.dao.NoteDao
import com.sobhanmp.data.local.db.NoteDatabase
import com.sobhanmp.data.mapper.toNoteEntity
import com.sobhanmp.data.mapper.toNoteModel
import com.sobhanmp.domain.model.NoteModel
import com.sobhanmp.domain.repository.NoteRepository
import com.sobhanmp.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDatabase: NoteDatabase
) : NoteRepository {

    private val dao = noteDatabase.dao
    override suspend fun saveNewNote(note: NoteModel): Flow<Resource<Unit>> {
        return flow<Resource<Unit>> {
            emit(Resource.Loading(true))

            val result = dao.insertNewNote(note.toNoteEntity())
            if (result > 0) {
                emit(Resource.Success<Unit>(null))
            } else {
                emit(Resource.Error<Unit>("Failed to save the note"))
            }
            emit(Resource.Loading(false))
        }

    }

    override suspend fun updateNote(note: NoteModel): Flow<Resource<Unit>> {
        return flow {
            emit(Resource.Loading(true))

            val result = dao.updateNote(note.toNoteEntity(note.id))
            if (result > 0) {
                emit(Resource.Success<Unit>(null))
            } else {
                emit(Resource.Error<Unit>("Failed to update the note"))
            }
            emit(Resource.Loading(false))
        }
    }

    override suspend fun deleteNote(note: NoteModel): Flow<Resource<Unit>> {
        return flow {
            emit(Resource.Loading(true))

            val result = dao.deleteNote(note.toNoteEntity(note.id))
            if (result > 0) {
                emit(Resource.Success<Unit>(null))
            } else {
                emit(Resource.Error<Unit>("Failed to update the note"))
            }
            emit(Resource.Loading(false))
        }
    }

    override suspend fun getNotesList(): Flow<Resource<List<NoteModel>>> {
        return flow {
            emit(Resource.Loading(true))
            val result = dao.getNotes()
            emit(Resource.Success<List<NoteModel>>(result.map { it.toNoteModel() }))
            emit(Resource.Loading(false))
        }
    }
}