package com.sobhanmp.domain.usecase

import com.sobhanmp.domain.model.NoteModel
import com.sobhanmp.domain.repository.NoteRepository
import com.sobhanmp.domain.util.Resource
import kotlinx.coroutines.flow.Flow

class GetAllNotesUseCase(private val noteRepository: NoteRepository) {

    suspend operator fun invoke(): Flow<Resource<List<NoteModel>>> {
        return noteRepository.getNotesList()
    }
}