package com.example.roomdbstudy.repository

import com.example.roomdbstudy.data.local.dto.NoteDao
import com.example.roomdbstudy.data.local.entity.Note
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotesRepository @Inject constructor(
   private val noteDao: NoteDao
) {
    val allNotes: Flow<List<Note>> = noteDao.getAllNotes()
    suspend fun insertNote(note: Note) = noteDao.insert(note)
    suspend fun deleteNote(note:Note) = noteDao.delete(note)
}