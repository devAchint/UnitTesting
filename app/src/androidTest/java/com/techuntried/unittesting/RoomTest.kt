package com.techuntried.unittesting

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.techuntried.unittesting.database.NotesDao
import com.techuntried.unittesting.database.NotesDatabase
import com.techuntried.unittesting.database.NotesEntity
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class RoomTest {
    private lateinit var db: NotesDatabase
    private lateinit var notesDao: NotesDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, NotesDatabase::class.java).build()
        notesDao = db.getNotesDao()
    }

    @Test
    fun insertNote() {
        runTest {
            val note = NotesEntity(title = "title", content = "content")
            notesDao.insertNote(note)
            Assert.assertEquals(note.title, notesDao.getAllNotes().first().title)
        }
    }

    @After
    fun tearDown() {
        db.close()
    }

}