package com.techuntried.unittesting

import com.techuntried.unittesting.database.NotesDao
import com.techuntried.unittesting.database.NotesEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RoomRepository @Inject constructor(private val notesDao: NotesDao) {

    suspend fun insertNote(notesEntity: NotesEntity) {
        withContext(Dispatchers.IO) {
            notesDao.insertNote(notesEntity)
        }
    }

    suspend fun deleteNoteById(noteId: Int) {
        withContext(Dispatchers.IO) {
            notesDao.deleteNoteById(noteId)
        }
    }

    fun getAllNotes(): Flow<List<NotesEntity>> {
        return notesDao.getAllNotesFlow().flowOn(Dispatchers.IO)
    }

    suspend fun getNoteById(noteId: Int): NotesEntity {
        return withContext(Dispatchers.IO) {
            notesDao.getNoteById(noteId)
        }
    }
}