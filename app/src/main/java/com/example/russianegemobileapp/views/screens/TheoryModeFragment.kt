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
import androidx.fragment.app.viewModels
import com.example.russianegemobileapp.R
import com.example.russianegemobileapp.databinding.FragmentTheoryModeBinding
import com.example.russianegemobileapp.viewModels.TheoryExModeViewModel
import com.example.russianegemobileapp.viewModels.factories.TheoryExModeViewModelFactory
import com.example.russianegemobileapp.views.MainActivity

class TheoryModeFragment : Fragment() {
    lateinit var binding : FragmentTheoryModeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTheoryModeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        btnsBinding()
    }
    fun btnsBinding(){
        binding.backBtn?.setOnClickListener {
            (activity as MainActivity).navController.navigate(R.id.action_theoryModeFragment_to_modeSelectionFragment)
        }
        binding.btns?.forEach {
            it.setOnClickListener{
                val bundle = Bundle()
                with(it as LinearLayout){
                    val tv = it.getChildAt(0) as TextView
                    val numberOfTask = tv.text.toString().slice(0..1).replace(".","").toInt()

                    bundle.putInt("numberOfTask",numberOfTask)
                }
                (activity as MainActivity).navController.navigate(R.id.action_theoryModeFragment_to_theoryExModeFragment,bundle)
            }


        }
    }

}