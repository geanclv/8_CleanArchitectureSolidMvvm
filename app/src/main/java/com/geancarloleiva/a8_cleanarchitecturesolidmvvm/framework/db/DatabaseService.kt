package com.geancarloleiva.a8_cleanarchitecturesolidmvvm.framework.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NoteEntity::class], version = 1)
abstract class DatabaseService : RoomDatabase() {

    companion object {
        private const val DATABASE_NAME = "notepath.db"
        private var instance: DatabaseService? = null

        private fun create(context: Context): DatabaseService = Room.databaseBuilder(
            context,
            DatabaseService::class.java,
            DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()

        //Singleton instance
        fun getInstance(context: Context): DatabaseService = (instance?: create(context)).also {
            instance = it
        }
    }

    abstract fun noteDao(): NoteDao
}