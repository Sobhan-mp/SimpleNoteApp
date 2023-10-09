package com.sobhanmp.simplenoteapp.detailscreen

import android.text.format.DateFormat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sobhanmp.domain.model.NoteModel
import com.sobhanmp.domain.util.DateUtil
import com.sobhanmp.domain.util.Resource
import com.sobhanmp.simplenoteapp.di.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(private val useCase: NoteUseCases) : ViewModel() {
    val title = MutableStateFlow<String>("")
    val description = MutableStateFlow<String>("")

    val date = DateUtil.getTodayDate()

    private val _isLoading: MutableSharedFlow<Boolean> = MutableSharedFlow(0)
    val isLoading: SharedFlow<Boolean> = _isLoading

    private val _error: MutableSharedFlow<String> = MutableSharedFlow(0)
    val error: SharedFlow<String> = _error

    private val _noteSaved: MutableSharedFlow<Boolean> = MutableSharedFlow(0)
    val noteSaved: SharedFlow<Boolean> = _noteSaved
    fun saveNote() {
        val date = Date()
        val s: CharSequence = DateFormat.format("MMMM d, yyyy ", date.getTime())
        val note = NoteModel(title = title.value!!, text = description.value, date = s.toString())

        viewModelScope.launch(Dispatchers.IO) {
            useCase.newNoteUseCase.invoke(note).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        _error.emit(result.error)
                    }

                    is Resource.Loading -> {
                        _isLoading.emit(result.isLoading)
                    }

                    is Resource.Success -> {
                        _noteSaved.emit(true)
                    }
                }
            }
        }

    }

    fun updateTitle(title: String){
        viewModelScope.launch(Dispatchers.Main) {
            this@DetailScreenViewModel.title.emit(title)
        }
    }

    fun updateDescription(description: String){
        viewModelScope.launch(Dispatchers.Main) {
            this@DetailScreenViewModel.description.emit(description)
        }
    }

}