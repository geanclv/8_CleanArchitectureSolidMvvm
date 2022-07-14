package com.geancarloleiva.a8_cleanarchitecturesolidmvvm.framework.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.geancarloleiva.core.data.Note

@Entity(tableName = "note")
data class NoteEntity (
    var title: String,
    var content: String,
    @ColumnInfo(name = "creation_date")
    var creationTime: Long,
    @ColumnInfo(name = "update_date")
    var updateTime: Long,
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L
) {

    companion object {
        fun fromNote(note: Note): NoteEntity{
            return NoteEntity(note.title, note.content, note.creationTime, note.updateTime)
        }
    }

    fun toNote(): Note{
        return Note(title, content, creationTime, updateTime, id)
    }
}