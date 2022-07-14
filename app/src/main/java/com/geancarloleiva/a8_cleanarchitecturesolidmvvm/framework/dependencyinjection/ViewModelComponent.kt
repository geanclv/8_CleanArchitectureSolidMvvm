package com.geancarloleiva.a8_cleanarchitecturesolidmvvm.framework.dependencyinjection

import com.geancarloleiva.a8_cleanarchitecturesolidmvvm.framework.viewmodel.ListViewModel
import com.geancarloleiva.a8_cleanarchitecturesolidmvvm.framework.viewmodel.NoteViewModel
import dagger.Component

@Component(modules = [ApplicationModule::class, RepositoryModule::class, UseCasesModule::class])
interface ViewModelComponent {

    fun inject(noteViewModel: NoteViewModel)

    fun inject(listViewModel: ListViewModel)
}