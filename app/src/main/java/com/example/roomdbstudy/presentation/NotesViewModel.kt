package com.example.roomdbstudy.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roomdbstudy.data.local.entity.Note
import com.example.roomdbstudy.repository.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val notesRepository: NotesRepository
): ViewModel() {

    private val _allNotes = notesRepository.allNotes
    private val _uiState = MutableStateFlow<NoteState>(NoteState.Loading)
    val uiState: StateFlow<NoteState> = _uiState


    init {
        loadNotes()
    }
    fun loadNotes(){
        viewModelScope.launch {
                notesRepository.allNotes
                    .catch { e -> _uiState.value = NoteState.Error(e.message ?: "Unknown error") }
                    .collect { notes ->
                        _uiState.value = if (notes.isEmpty()) NoteState.Empty
                        else NoteState.Success(notes)
                    }
        }
    }

    fun insertNote(note: Note){
        viewModelScope.launch {
            notesRepository.insertNote(note)
        }
    }


    fun deleteNote(note: Note){
        viewModelScope.launch {
            notesRepository.deleteNote(note)
        }
    }




}

sealed class NoteState{
    object Loading: NoteState()
    object Empty : NoteState()
    data class Success(val data:List<Note>): NoteState()
    data class Error(val message:String): NoteState()
}