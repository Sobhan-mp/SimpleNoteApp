package com.sobhanmp.simplenoteapp.notelistscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sobhanmp.domain.model.NoteModel
import com.sobhanmp.domain.util.Resource
import com.sobhanmp.simplenoteapp.di.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel(

) {
    fun start() {
        getAllNotes()
    }

    private val _notesList: MutableStateFlow<List<NoteModel>?> = MutableStateFlow<List<NoteModel>?>(
        emptyList()
    )
    val notesList: StateFlow<List<NoteModel>?> = _notesList
    private fun getAllNotes() {
        viewModelScope.launch(Dispatchers.IO) {
            noteUseCases.getAllNotesUseCase.invoke().collect { result ->
                when (result) {
                    is Resource.Error -> {}
                    is Resource.Loading -> {

                    }
                    is Resource.Success -> {
                        _notesList.emit(result.data)
                    }
                }
            }
        }
    }
}