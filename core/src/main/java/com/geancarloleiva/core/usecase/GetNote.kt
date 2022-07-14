package com.geancarloleiva.core.usecase

import com.geancarloleiva.core.repository.NoteRepository

class GetNote(private val noteRepository: NoteRepository) {

    suspend operator fun invoke(id: Long){
        noteRepository.getNote(id)
    }
}