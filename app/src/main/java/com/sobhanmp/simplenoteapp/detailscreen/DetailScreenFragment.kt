package com.sobhanmp.simplenoteapp.detailscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.sobhanmp.simplenoteapp.R
import com.sobhanmp.simplenoteapp.databinding.FragmentDetailScreenBinding


class DetailScreenFragment : Fragment() {


    private lateinit var binding: FragmentDetailScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_detail_screen, container, false)
        return binding.root
    }


}