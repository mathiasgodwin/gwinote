package com.mathiasgodwin.gwinote.data.local.dao

import com.mathiasgodwin.gwinote.data.local.entity.NoteEntity
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addNote(noteEntity: NoteEntity)
    @Update
    suspend fun updateNote(noteEntity: NoteEntity)
    @Delete
    suspend fun deleteNote(noteEntity: NoteEntity)
    @Query("SELECT * FROM noteentity WHERE id = :id")
    suspend fun getNoteById(id: Int): NoteEntity?
    @Query("SELECT * FROM noteentity")
    fun getAllNotes(): Flow<List<NoteEntity>>
}