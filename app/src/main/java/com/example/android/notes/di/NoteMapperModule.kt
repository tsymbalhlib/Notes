package com.example.android.notes.di

import com.example.android.notes.mappers.NoteMapper
import com.example.android.notes.mappers.NoteMapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class NoteMapperModule {

    @Binds
    abstract fun bindsNoteMapper(impl: NoteMapperImpl): NoteMapper
}