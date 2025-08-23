package com.mathiasgodwin.gwinote.data.local.repo

import com.mathiasgodwin.gwinote.data.local.entity.NoteEntity
import com.mathiasgodwin.gwinote.data.local.dao.NoteDao
import kotlinx.coroutines.flow.Flow

class AppRepository(
    private val noteDao: NoteDao,
) {

    suspend fun addNote(noteEntity: NoteEntity) {
        noteDao.addNote(noteEntity)
    }

    suspend fun updateNote(noteEntity: NoteEntity) {
        noteDao.updateNote(noteEntity)
    }

    suspend fun deleteNote(noteEntity: NoteEntity) {
        noteDao.deleteNote(noteEntity)
    }

    fun getNotes(): Flow<List<NoteEntity>> {
        return noteDao.getAllNotes()
    }

}