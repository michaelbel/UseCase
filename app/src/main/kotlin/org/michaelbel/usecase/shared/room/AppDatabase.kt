package org.michaelbel.usecase.shared.room

import androidx.room.Database
import androidx.room.RoomDatabase
import org.michaelbel.usecase.shared.room.dao.NoteDao
import org.michaelbel.usecase.shared.room.entity.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = AppDatabase.DATABASE_VERSION,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {
        const val DATABASE_NAME = "usecase.db"
        const val DATABASE_VERSION = 3
    }
}
