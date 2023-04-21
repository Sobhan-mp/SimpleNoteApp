package com.sobhanmp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sobhanmp.data.local.dao.NoteDao
import com.sobhanmp.data.local.entity.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class NoteDatabase: RoomDatabase() {
    abstract val dao : NoteDao
}