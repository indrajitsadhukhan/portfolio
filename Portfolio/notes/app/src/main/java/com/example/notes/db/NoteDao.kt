package com.example.notes.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {
    @Insert
    suspend fun addNote(note: Note)
    @Query(value = "Select * from note order by id DESC")
   suspend fun getAllNotes():List<Note>
    @Insert
   suspend fun addMultipleNotes(vararg note: Note)
   @Update
   suspend fun updateNote(note: Note)

}