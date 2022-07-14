package com.geancarloleiva.a8_cleanarchitecturesolidmvvm.framework.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.geancarloleiva.a8_cleanarchitecturesolidmvvm.framework.RoomNoteDataSource
import com.geancarloleiva.a8_cleanarchitecturesolidmvvm.framework.UseCases
import com.geancarloleiva.a8_cleanarchitecturesolidmvvm.framework.dependencyinjection.ApplicationModule
import com.geancarloleiva.a8_cleanarchitecturesolidmvvm.framework.dependencyinjection.DaggerViewModelComponent
import com.geancarloleiva.core.data.Note
import com.geancarloleiva.core.repository.NoteRepository
import com.geancarloleiva.core.usecase.AddNote
import com.geancarloleiva.core.usecase.GetAllNotes
import com.geancarloleiva.core.usecase.GetNote
import com.geancarloleiva.core.usecase.RemoveNote
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListViewModel(application: Application) : AndroidViewModel(application) {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    //Replacing this repository and useCases by an injection
    /*private val repository = NoteRepository(RoomNoteDataSource(application))
    val useCases = UseCases(
        AddNote(repository),
        GetNote(repository),
        GetAllNotes(repository),
        RemoveNote(repository)
    )*/
    @Inject
    lateinit var useCases: UseCases

    init {
        DaggerViewModelComponent.builder()
            .applicationModule(ApplicationModule(getApplication()))
            .build()
            .inject(this)
    }

    val lstNotes = MutableLiveData<List<Note>>()

    fun getNotes() {
        coroutineScope.launch {
            val notes: List<Note> = useCases.getAllNotes.invoke()
            notes.forEach { it.wordCount = useCases.getWordCount.invoke(it) }
            lstNotes.postValue(notes)
        }
    }
}