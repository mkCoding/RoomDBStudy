package com.example.roomdbstudy.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

// Entity Class represents a table
@Entity(tableName = "note_table") // table name
data class Note(
    // table columns
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val title:String,
    val description:String
)
