package com.geancarloleiva.core.usecase

import com.geancarloleiva.core.data.Note
import com.geancarloleiva.core.repository.NoteRepository

class RemoveNote(private val noteRepository: NoteRepository) {

    suspend operator fun invoke(note: Note){
        noteRepository.removeNote(note)
    }
}