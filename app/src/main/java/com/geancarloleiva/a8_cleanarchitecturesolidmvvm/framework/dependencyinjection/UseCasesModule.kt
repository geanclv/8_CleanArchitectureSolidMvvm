package com.geancarloleiva.a8_cleanarchitecturesolidmvvm.framework.dependencyinjection

import com.geancarloleiva.a8_cleanarchitecturesolidmvvm.framework.UseCases
import com.geancarloleiva.core.repository.NoteRepository
import com.geancarloleiva.core.usecase.*
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
            RemoveNote(repository),
            GetWordCount() //No needs repository because it uses the data from note itself
        )
    }
}