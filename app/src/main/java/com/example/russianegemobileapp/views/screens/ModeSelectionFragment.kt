package com.example.russianegemobileapp.views.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.russianegemobileapp.R
import com.example.russianegemobileapp.databinding.FragmentModeSelectionBinding
import com.example.russianegemobileapp.views.MainActivity


class ModeSelectionFragment : Fragment() {
    lateinit var binding:FragmentModeSelectionBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentModeSelectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        btnsBinding()
    }

    fun btnsBinding(){
        binding.backBtn?.setOnClickListener {
            (activity as MainActivity).navController.navigate(R.id.action_modeSelectionFragment_to_mainFragment)
        }
        binding.bTheory?.setOnClickListener {
            (activity as MainActivity).navController.navigate(R.id.action_modeSelectionFragment_to_theoryModeFragment)
        }
    }


}