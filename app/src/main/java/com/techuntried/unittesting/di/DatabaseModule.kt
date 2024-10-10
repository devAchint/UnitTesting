package com.techuntried.unittesting.di

import android.content.Context
import androidx.room.Room
import com.techuntried.unittesting.database.NotesDao
import com.techuntried.unittesting.database.NotesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context):NotesDatabase{
        return Room.databaseBuilder(context,NotesDatabase::class.java,"notesDb").build()
    }

    @Provides
    @Singleton
    fun providesNotesDao(db:NotesDatabase):NotesDao{
        return db.getNotesDao()
    }
}

