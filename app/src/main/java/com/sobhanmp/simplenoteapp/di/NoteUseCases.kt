package com.sobhanmp.simplenoteapp.di

import com.sobhanmp.domain.usecase.DeleteNoteUseCase
import com.sobhanmp.domain.usecase.GetAllNotesUseCase
import com.sobhanmp.domain.usecase.SaveNewNoteUseCase
import com.sobhanmp.domain.usecase.UpdateNoteUseCase

class NoteUseCases(
    val newNoteUseCase: SaveNewNoteUseCase,
    val updateNoteUseCase: UpdateNoteUseCase,
    val deleteNoteUseCase: DeleteNoteUseCase,
    val getAllNotesUseCase: GetAllNotesUseCase
) {
}