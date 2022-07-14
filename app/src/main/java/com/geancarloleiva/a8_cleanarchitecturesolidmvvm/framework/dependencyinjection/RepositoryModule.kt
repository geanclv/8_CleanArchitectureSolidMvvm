package com.geancarloleiva.a8_cleanarchitecturesolidmvvm.framework.dependencyinjection

import android.app.Application
import com.geancarloleiva.a8_cleanarchitecturesolidmvvm.framework.RoomNoteDataSource
import com.geancarloleiva.core.repository.NoteRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideRepository(app: Application): NoteRepository{
        return NoteRepository(RoomNoteDataSource(app))
    }
}