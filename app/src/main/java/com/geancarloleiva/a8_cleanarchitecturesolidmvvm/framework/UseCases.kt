package com.geancarloleiva.a8_cleanarchitecturesolidmvvm.framework

import com.geancarloleiva.core.usecase.*

data class UseCases (
    val addNote: AddNote,
    val getNote: GetNote,
    val getAllNotes: GetAllNotes,
    val removeNote: RemoveNote,
    val getWordCount: GetWordCount
    ) {
}