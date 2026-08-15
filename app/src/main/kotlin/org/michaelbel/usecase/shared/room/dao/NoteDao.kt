package org.michaelbel.usecase.shared.room.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import org.michaelbel.usecase.shared.room.entity.NoteEntity

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes ORDER BY id")
    fun selectAllFlow(): Flow<List<NoteEntity>>

    @Upsert
    suspend fun upsert(vararg entity: NoteEntity)

    @Query("DELETE FROM notes WHERE id = :id")
    suspend fun delete(id: Int)
}
