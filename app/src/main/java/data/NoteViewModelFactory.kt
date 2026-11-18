package data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras

class NoteViewModelFactory(private val repository: NoteRepository): ViewModelProvider.Factory {


        override fun <T : ViewModel> create(
            modelClass: Class<T>,
            extras: CreationExtras
        ): T {

            if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {

                @Suppress("UNCHECKED_CAST")
                return NoteViewModel(repository) as T
            }

            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }


