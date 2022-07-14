package com.geancarloleiva.core.repository

import com.geancarloleiva.core.data.Note

interface NoteDataSource {

    //Suspend indicates that the function can be paused and resumed at a later time
    suspend fun add(note: Note)

    suspend fun get(id: Long): Note?

    suspend fun getAll(): List<Note>

    suspend fun remove(note: Note)
}