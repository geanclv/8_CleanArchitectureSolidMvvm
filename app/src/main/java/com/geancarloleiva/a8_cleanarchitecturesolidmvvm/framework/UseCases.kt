package com.geancarloleiva.a8_cleanarchitecturesolidmvvm.framework

import com.geancarloleiva.core.usecase.AddNote
import com.geancarloleiva.core.usecase.GetAllNotes
import com.geancarloleiva.core.usecase.GetNote
import com.geancarloleiva.core.usecase.RemoveNote

data class UseCases (
    val addNote: AddNote,
    val getNote: GetNote,
    val getAllNotes: GetAllNotes,
    val removeNote: RemoveNote
    ) {
}