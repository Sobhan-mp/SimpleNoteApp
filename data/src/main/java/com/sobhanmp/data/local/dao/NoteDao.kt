package com.sobhanmp.data.local.dao

import androidx.room.*
import com.sobhanmp.data.local.entity.NoteEntity

@Dao
interface NoteDao {

    @Insert
    suspend fun insertNewNote(noteEntity: NoteEntity): Long

    @Delete
    suspend fun deleteNote(noteEntity: NoteEntity): Int

    @Update
    suspend fun updateNote(noteEntity: NoteEntity): Int

    @Query("SELECT * FROM note")
    suspend fun getNotes(): List<NoteEntity>
}