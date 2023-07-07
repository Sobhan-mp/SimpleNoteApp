package com.sobhanmp.simplenoteapp.detailscreen

import android.text.format.DateFormat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sobhanmp.domain.model.NoteModel
import com.sobhanmp.domain.util.DateUtil
import com.sobhanmp.simplenoteapp.di.NoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(private val useCase: NoteUseCases) : ViewModel() {
    val title = MutableStateFlow<String>("")
    val description = MutableStateFlow<String>("")

    val date = DateUtil.getTodayDate()
    fun saveNote(){
        val date = Date()
        val s: CharSequence = DateFormat.format("MMMM d, yyyy ", date.getTime())
        val note= NoteModel(title = title.value, text = description.value, date = s.toString())

        viewModelScope.launch(Dispatchers.IO) {
            useCase.newNoteUseCase.invoke(note)
        }

    }

}