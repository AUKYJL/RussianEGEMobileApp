package com.example.russianegemobileapp.views.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.russianegemobileapp.R
import com.example.russianegemobileapp.databinding.FragmentDictModeBinding
import com.example.russianegemobileapp.models.Constants
import com.example.russianegemobileapp.views.MainActivity

class DictModeFragment : Fragment() {
    lateinit var binding: FragmentDictModeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDictModeBinding.inflate(inflater,container,false)
        btnsBinding()
        return binding.root
    }
    fun btnsBinding(){
        binding.backBtn?.setOnClickListener {
            (activity as MainActivity).navController.navigate(R.id.action_dictModeFragment_to_modeSelectionFragment)

        }
        binding.bDictParonyms?.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(Constants.BUNDLE_DICT_KEY,Constants.BUNDLE_DICT_PUT_PARONYM)
            (activity as MainActivity).navController.navigate(R.id.action_dictModeFragment_to_dictInModeFragment,bundle)

        }
        binding.bDictOrhoepy?.setOnClickListener {
            val bundle = Bundle()
            bundle.putString(Constants.BUNDLE_DICT_KEY,Constants.BUNDLE_DICT_PUT_ORTHOEPY)
            (activity as MainActivity).navController.navigate(R.id.action_dictModeFragment_to_dictInModeFragment,bundle)

        }
    }

}