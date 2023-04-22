package com.sobhanmp.simplenoteapp.di

import com.sobhanmp.domain.repository.NoteRepository
import com.sobhanmp.domain.usecase.DeleteNoteUseCase
import com.sobhanmp.domain.usecase.GetAllNotesUseCase
import com.sobhanmp.domain.usecase.SaveNewNoteUseCase
import com.sobhanmp.domain.usecase.UpdateNoteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            newNoteUseCase = SaveNewNoteUseCase(repository),
            deleteNoteUseCase = DeleteNoteUseCase(repository),
            updateNoteUseCase = UpdateNoteUseCase(repository),
            getAllNotesUseCase = GetAllNotesUseCase(repository)
        )
    }
}