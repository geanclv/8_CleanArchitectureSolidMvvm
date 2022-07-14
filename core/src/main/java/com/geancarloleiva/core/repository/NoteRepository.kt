package com.geancarloleiva.core.repository

import com.geancarloleiva.core.data.Note

class NoteRepository(private val dataSource: NoteDataSource) {

    suspend fun addNote(note: Note){
        dataSource.add(note)
    }

    suspend fun getNote(id: Long): Note? {
        return dataSource.get(id)
    }

    suspend fun getAllNotes(): List<Note> {
        return dataSource.getAll()
    }

    suspend fun removeNote(note: Note){
        dataSource.remove(note)
    }
}