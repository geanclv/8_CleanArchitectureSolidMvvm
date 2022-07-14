package com.geancarloleiva.a8_cleanarchitecturesolidmvvm.framework.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.geancarloleiva.a8_cleanarchitecturesolidmvvm.framework.RoomNoteDataSource
import com.geancarloleiva.a8_cleanarchitecturesolidmvvm.framework.UseCases
import com.geancarloleiva.core.data.Note
import com.geancarloleiva.core.repository.NoteRepository
import com.geancarloleiva.core.usecase.AddNote
import com.geancarloleiva.core.usecase.GetAllNotes
import com.geancarloleiva.core.usecase.GetNote
import com.geancarloleiva.core.usecase.RemoveNote
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application): AndroidViewModel(application) {

    //Communicating with Room BD in background using Coroutines
    //Dispatchers.IO is more efficient for communicate with remote dataSources (web, db, etc)
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val repository = NoteRepository(RoomNoteDataSource(application))

    val useCases = UseCases(
        AddNote(repository),
        GetNote(repository),
        GetAllNotes(repository),
        RemoveNote(repository)
    )

    val saved = MutableLiveData<Boolean>()
    val currentNote = MutableLiveData<Note?>()
    val deleted = MutableLiveData<Boolean>()

    fun saveNote(note: Note){
        coroutineScope.launch {
            useCases.addNote(note)
            saved.postValue(true)
        }
    }

    fun getNoteById(id: Long){
        coroutineScope.launch {
            val note = useCases.getNote(id)
            currentNote.postValue(note)
        }
    }

    fun deleteNote(note: Note){
        coroutineScope.launch {
            useCases.removeNote(note)
            deleted.postValue(true)
        }
    }
}