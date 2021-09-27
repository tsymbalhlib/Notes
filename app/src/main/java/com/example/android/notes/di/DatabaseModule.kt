package com.example.android.notes.di

import android.content.Context
import androidx.room.Room
import com.example.android.notes.database.NoteDatabase
import com.example.android.notes.database.NoteDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): NoteDatabase {
        return Room.databaseBuilder(
            appContext.applicationContext,
            NoteDatabase::class.java,
            "notes_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideDatabaseDao(database: NoteDatabase): NoteDatabaseDao {
        return database.noteDatabaseDao
    }
}