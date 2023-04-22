package com.sobhanmp.data.di

import android.app.Application
import androidx.room.Room
import com.sobhanmp.data.local.dao.NoteDao
import com.sobhanmp.data.local.db.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    fun dataBaseProvider(application: Application): NoteDatabase {
        return Room.databaseBuilder(application, NoteDatabase::class.java, "note_database").build()
    }

    @Provides
    fun daoProvider(noteDatabase: NoteDatabase): NoteDao{
        return noteDatabase.dao
    }

}