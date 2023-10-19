package com.sobhanmp.simplenoteapp.notelistscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.sobhanmp.domain.model.NoteModel
import com.sobhanmp.simplenoteapp.R
import com.sobhanmp.simplenoteapp.databinding.FragmentHomeBinding
import com.sobhanmp.simplenoteapp.extention.collectFlow
import com.sobhanmp.simplenoteapp.notelistscreen.adapter.NoteListAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by activityViewModels<HomeScreenViewModel>()
    private val adapter by lazy<NoteListAdapter> {
        NoteListAdapter().apply {
            setOnItemClickListener(object : NoteListAdapter.OnItemClickListener{
            override fun onClick(noteModel: NoteModel?) {
                val bundle = Bundle()
                bundle.putSerializable("note_item", noteModel)
                Navigation.findNavController(requireView()).navigate(R.id.action_homeFragment_to_detailScreenFragment, bundle)
            }
        })
        }

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            recyclerView.adapter = adapter
            floatingButton.setOnClickListener {
                Navigation.findNavController(requireView()).navigate(R.id.action_homeFragment_to_detailScreenFragment)
            }
        }

        collectFlow(viewModel.notesList){
            adapter.setNoteList(it)
        }

        viewModel.start()


    }

}