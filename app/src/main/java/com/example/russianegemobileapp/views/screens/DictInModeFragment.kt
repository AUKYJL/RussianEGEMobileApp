package com.example.russianegemobileapp.views.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.russianegemobileapp.R
import com.example.russianegemobileapp.databinding.FragmentDictInModeBinding
import com.example.russianegemobileapp.views.MainActivity

class DictInModeFragment : Fragment() {
    lateinit var binding:FragmentDictInModeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDictInModeBinding.inflate(inflater,container,false)
        btnsBinding()
        return binding.root
    }
    fun btnsBinding(){
        binding.backBtn?.setOnClickListener {
            (activity as MainActivity).navController.navigate(R.id.action_dictInModeFragment_to_dictModeFragment)
        }
    }

}