package com.techuntried.unittesting.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {
    @Insert
    suspend fun insertNote(notesEntity: NotesEntity)

    @Query("SELECT * FROM notes")
    fun getAllNotesFlow(): Flow<List<NotesEntity>>

    @Query("SELECT * FROM notes")
    fun getAllNotes(): List<NotesEntity>

    @Query("SELECT * FROM notes WHERE id = :id")
    suspend fun getNoteById(id: Int): NotesEntity

    @Query("DELETE FROM notes WHERE id = :id")
    suspend fun deleteNoteById(id: Int)

}