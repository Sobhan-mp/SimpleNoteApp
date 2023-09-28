package com.sobhanmp.simplenoteapp.detailscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import com.sobhanmp.domain.util.DateUtil
import com.sobhanmp.simplenoteapp.R
import com.sobhanmp.simplenoteapp.databinding.FragmentDetailScreenBinding
import com.sobhanmp.simplenoteapp.extention.collectFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DetailScreenFragment : Fragment() {


    private lateinit var binding: FragmentDetailScreenBinding
    private val viewModel by activityViewModels<DetailScreenViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_detail_screen, container, false)
        binding.viewmodel = viewModel

        binding.button.setOnClickListener {
            viewModel.saveNote()
        }

        collectFlows()
        return binding.root
    }

    private fun collectFlows() {
        collectFlow(viewModel.error){
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        collectFlow(viewModel.isLoading){

        }

        collectFlow(viewModel.noteSaved){
            Navigation.findNavController(requireView()).navigateUp()
        }
    }


}