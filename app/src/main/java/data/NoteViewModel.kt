package data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NoteViewModel(private val repository: NoteRepository): ViewModel() {
    private val _notes = MutableStateFlow<List<Notes>>(emptyList())
    val notes : StateFlow<List<Notes>> = _notes.asStateFlow()
    init {
        getNotes()
    }
    fun getNotes(){
        viewModelScope.launch {
            _notes.value = repository.getAllNotes()
        }
    }
    fun insertNote(note: Notes){
        viewModelScope.launch {
            repository.insertNote(note)
            getNotes()
        }
    }
    fun deleteNote(note: Notes){
        viewModelScope.launch {
            repository.deleteNote(note)
            getNotes()
        }
    }
    fun updateNote(note: Notes
    ,onSuccess:()-> Unit){
        viewModelScope.launch {
            repository.updateNote(note)
            _notes.value = repository.getAllNotes()
            onSuccess()
        }
    }
}