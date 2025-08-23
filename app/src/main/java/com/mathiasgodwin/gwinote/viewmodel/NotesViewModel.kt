package com.mathiasgodwin.gwinote.viewmodel

import com.mathiasgodwin.gwinote.data.local.entity.NoteEntity
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.mathiasgodwin.gwinote.data.local.dao.AppDatabase
import com.mathiasgodwin.gwinote.data.local.repo.AppRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NotesViewModel(
    private val application: Application
) : ViewModel() {
    private val dao = AppDatabase.getDatabase(application).noteDao()
    private val repository = AppRepository(dao)
    private val _notes = MutableStateFlow<List<NoteEntity>>(emptyList())
    val notes: StateFlow<List<NoteEntity>> = _notes

    init {
        viewModelScope.launch {
            repository.getNotes().collect {
                _notes.value = it
            }
        }
    }

    fun addNote(noteEntity: NoteEntity) {
        viewModelScope.launch {
            repository.addNote(noteEntity)
        }
    }

    fun updateNote(noteEntity: NoteEntity) {
        viewModelScope.launch {
            repository.updateNote(noteEntity)
        }
    }

    fun deleteNote(noteEntity: NoteEntity) {
        viewModelScope.launch {
            repository.deleteNote(noteEntity)
        }
    }

    fun getNoteById(id: Int): NoteEntity? {
        return notes.value.find { it.id == id }
    }

}

class NotesViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NotesViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}