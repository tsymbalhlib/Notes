package com.example.android.notes.di

import com.example.android.notes.mappers.DatabaseMapper
import com.example.android.notes.mappers.DatabaseMapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DatabaseMapperModule {

    @Binds
    abstract fun bindsDatabaseMapper(impl: DatabaseMapperImpl): DatabaseMapper
}