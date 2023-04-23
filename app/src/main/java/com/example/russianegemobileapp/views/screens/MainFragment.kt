package com.example.russianegemobileapp.views.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.russianegemobileapp.R
import com.example.russianegemobileapp.databinding.FragmentMainBinding
import com.example.russianegemobileapp.views.MainActivity
import kotlin.system.exitProcess

class MainFragment : Fragment() {
    lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        btnsBinding()

    }

    private fun btnsBinding(){
        binding.bExit?.setOnClickListener{
            (activity as MainActivity).finishAffinity()
            exitProcess(0)
        }
        binding.bStart?.setOnClickListener{
            (activity as MainActivity).navController.navigate(R.id.action_mainFragment_to_modeSelectionFragment)
        }
    }

}