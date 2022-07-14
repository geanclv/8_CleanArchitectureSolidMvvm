package com.geancarloleiva.a8_cleanarchitecturesolidmvvm.framework.dependencyinjection

import com.geancarloleiva.a8_cleanarchitecturesolidmvvm.framework.UseCases
import com.geancarloleiva.core.repository.NoteRepository
import com.geancarloleiva.core.usecase.AddNote
import com.geancarloleiva.core.usecase.GetAllNotes
import com.geancarloleiva.core.usecase.GetNote
import com.geancarloleiva.core.usecase.RemoveNote
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {

    @Provides
    fun getUseCases(repository: NoteRepository): UseCases {
        return UseCases(
            AddNote(repository),
            GetNote(repository),
            GetAllNotes(repository),
            RemoveNote(repository)
        )
    }
}