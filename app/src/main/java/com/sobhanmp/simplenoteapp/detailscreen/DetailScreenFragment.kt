package com.sobhanmp.simplenoteapp.detailscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.sobhanmp.domain.model.NoteModel
import com.sobhanmp.simplenoteapp.R
import com.sobhanmp.simplenoteapp.databinding.FragmentDetailScreenBinding
import com.sobhanmp.simplenoteapp.extention.collectFlow
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailScreenFragment : Fragment() {


    private lateinit var binding: FragmentDetailScreenBinding
    private val viewModel by viewModels<DetailScreenViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_detail_screen, container, false)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel

        binding.button.setOnClickListener {
            viewModel.saveNote()
        }

        binding.leftArrow.setOnClickListener {
            backNavigation()
        }

        binding.delete.setOnClickListener {
            deleteNote()
        }
        val noteItem =
            arguments?.getSerializable("note_item") as NoteModel?

        noteItem?.let {
            handlePassedArgument(it)
        }


        collectFlows()
        return binding.root
    }

    private fun handlePassedArgument(noteModel: NoteModel) {
        viewModel.title.value = noteModel.title
        viewModel.description.value = noteModel.text
        viewModel.id = noteModel.id

        binding.delete.visibility = View.VISIBLE
    }

    private fun deleteNote() {
        viewModel.deleteNote()
    }

    private fun backNavigation() {
        Navigation.findNavController(requireView()).navigateUp()
    }

    private fun collectFlows() {
        collectFlow(viewModel.error) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        collectFlow(viewModel.isLoading) {

        }

        collectFlow(viewModel.noteSaved) {
            backNavigation()
        }

        collectFlow(viewModel.noteDeleted) {
            backNavigation()
        }

        collectFlow(viewModel.title) {
            checkButtonEnableStatus()
        }

        collectFlow(viewModel.description) {
            checkButtonEnableStatus()
        }
    }

    private fun checkButtonEnableStatus() {
        val isEnable = !viewModel.title.value.isNullOrEmpty() && !viewModel.description.value.isNullOrEmpty()
        binding.button.isEnabled = isEnable

        binding.button.visibility =
            if (isEnable) View.VISIBLE else View.GONE
    }


}