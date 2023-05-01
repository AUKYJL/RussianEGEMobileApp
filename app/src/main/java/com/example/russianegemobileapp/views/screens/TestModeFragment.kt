package com.example.russianegemobileapp.views.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.forEach
import com.example.russianegemobileapp.R
import com.example.russianegemobileapp.databinding.FragmentTestModeBinding
import com.example.russianegemobileapp.models.Constants
import com.example.russianegemobileapp.views.MainActivity

class TestModeFragment : Fragment() {
    lateinit var binding: FragmentTestModeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTestModeBinding.inflate(inflater, container, false)
        btnsBinding()
        return binding.root
    }


    fun btnsBinding() {
        binding.backBtn?.setOnClickListener {
            (activity as MainActivity).navController.navigate(R.id.action_testModeFragment_to_modeSelectionFragment)
        }
        binding.tasks?.forEach {
            it.setOnClickListener {
                val bundle = Bundle()
                var numberOfTaskString:String
                with(it as LinearLayout) {
                    val tv = it.getChildAt(1) as TextView
                    numberOfTaskString = tv.text.toString()
                }

                try {
                    val numberOfTask = numberOfTaskString.toInt()
                    bundle.putInt(Constants.BUNDLE_TEST_KEY,numberOfTask)
                }catch(_:java.lang.Exception) {
                    //если все подряд, то передает 0
                    bundle.putInt(Constants.BUNDLE_TEST_KEY,0)
                }
                (activity as MainActivity).navController.navigate(
                    R.id.action_testModeFragment_to_testInModeFragment,
                    bundle
                )
            }
        }
    }

}