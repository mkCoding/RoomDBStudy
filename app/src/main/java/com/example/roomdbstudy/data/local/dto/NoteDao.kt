package com.example.roomdbstudy.data.local.dto

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomdbstudy.data.local.entity.Note
import kotlinx.coroutines.flow.Flow

// Define SQL queries in DAO

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note) //Note data class insert

    @Delete
    suspend fun delete(note:Note)

    @Query("SELECT * FROM note_table ORDER BY id ASC")
    fun getAllNotes(): Flow<List<Note>>
}