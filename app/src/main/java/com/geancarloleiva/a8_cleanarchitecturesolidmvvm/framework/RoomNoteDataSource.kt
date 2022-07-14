package com.geancarloleiva.a8_cleanarchitecturesolidmvvm.framework

import android.content.Context
import com.geancarloleiva.a8_cleanarchitecturesolidmvvm.framework.db.DatabaseService
import com.geancarloleiva.a8_cleanarchitecturesolidmvvm.framework.db.NoteEntity
import com.geancarloleiva.core.data.Note
import com.geancarloleiva.core.repository.NoteDataSource

class RoomNoteDataSource(context: Context): NoteDataSource {

    private val noteDao = DatabaseService.getInstance(context).noteDao()

    override suspend fun add(note: Note) {
        noteDao.addNoteEntity(NoteEntity.fromNote(note))
    }

    override suspend fun get(id: Long): Note? {
        return noteDao.getNoteEntity(id)?.toNote()
    }

    override suspend fun getAll(): List<Note> {
        return noteDao.getAllNoteEntities().map { it.toNote() }
    }

    override suspend fun remove(note: Note) {
        noteDao.removeNoteEntity(NoteEntity.fromNote(note))
    }
}