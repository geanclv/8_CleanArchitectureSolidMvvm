package com.geancarloleiva.core.usecase

import com.geancarloleiva.core.data.Note
import com.geancarloleiva.core.repository.NoteRepository

class AddNote(private val noteRepository: NoteRepository) {

    suspend operator fun invoke(note: Note){
        noteRepository.addNote(note)
    }
}