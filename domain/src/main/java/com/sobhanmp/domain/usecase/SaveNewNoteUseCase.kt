package com.sobhanmp.domain.usecase

import com.sobhanmp.domain.model.NoteModel
import com.sobhanmp.domain.repository.NoteRepository
import com.sobhanmp.domain.util.Resource
import kotlinx.coroutines.flow.Flow

class SaveNewNoteUseCase(private val noteRepository: NoteRepository) {

    suspend operator fun invoke(note: NoteModel): Flow<Resource<Unit>> {
        return noteRepository.saveNewNote(note = note)
    }
}