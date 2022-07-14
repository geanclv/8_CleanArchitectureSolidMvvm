package com.geancarloleiva.core.usecase

import com.geancarloleiva.core.repository.NoteRepository

class GetAllNotes(private val noteRepository: NoteRepository) {

    suspend operator fun invoke(){
        noteRepository.getAllNotes()
    }
}