package com.example.roomdbstudy.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomdbstudy.data.local.dto.NoteDao
import com.example.roomdbstudy.data.local.entity.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase(){
    abstract fun notesDatabase(): NoteDao
    companion object{
        @Volatile private var INSTANCE: NoteDatabase?=null

        fun getDatabase(context: Context): NoteDatabase{
            return INSTANCE?:synchronized(this){
                Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "notes.db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}