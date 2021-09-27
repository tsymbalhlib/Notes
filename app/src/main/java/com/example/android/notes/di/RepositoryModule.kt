package com.example.android.notes.di

import com.example.android.notes.repository.Repository
import com.example.android.notes.repository.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindsRepository(impl: RepositoryImpl): Repository
}