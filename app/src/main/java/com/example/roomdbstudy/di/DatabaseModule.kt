package com.example.roomdbstudy.di

import android.content.Context
import androidx.room.Room
import com.example.roomdbstudy.data.local.NoteDatabase
import com.example.roomdbstudy.data.local.dto.NoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): NoteDatabase {
        return Room.databaseBuilder(
            context,
            NoteDatabase::class.java,
            "notes.db"
        ).build()
    }

    @Provides
    @Singleton
    fun providesNotesDao(noteDatabase: NoteDatabase): NoteDao{
        return noteDatabase.notesDatabase()

    }
}


