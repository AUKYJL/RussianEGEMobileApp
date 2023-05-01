package com.example.russianegemobileapp.views.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.russianegemobileapp.R
import com.example.russianegemobileapp.databinding.FragmentGetAnswerPopUpBinding
import com.example.russianegemobileapp.databinding.FragmentTestInModeBinding
import com.example.russianegemobileapp.models.Constants
import com.example.russianegemobileapp.views.MainActivity


class TestInModeFragment : Fragment() {
    lateinit var binding: FragmentTestInModeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTestInModeBinding.inflate(inflater,container,false)
        val numberOfTask = arguments?.getInt(Constants.BUNDLE_TEST_KEY)!!

        btnsBinding()
        return binding.root
    }

    fun btnsBinding() {
        binding.backBtn?.setOnClickListener {
            (activity as MainActivity).navController.navigate(R.id.action_testInModeFragment_to_testModeFragment)
        }
        binding.getHelp?.setOnClickListener{
            val bundle = Bundle()
            val numberOfTask = arguments?.getInt(Constants.BUNDLE_TEST_KEY)!!
            bundle.putInt(Constants.BUNDLE_THEORY_KEY,numberOfTask)
            bundle.putBoolean(Constants.BUNDLE_IS_IT_FROM_THEORY_KEY,false)
            (activity as MainActivity).navController.navigate(R.id.action_testInModeFragment_to_theoryExModeFragment,bundle)
        }
        binding.getAnswer?.setOnClickListener{
            val showGetAnswerPopUp = GetAnswerPopUpFragment()
            showGetAnswerPopUp.show((activity as MainActivity).supportFragmentManager,"ShowPopUp")
        }
    }
}