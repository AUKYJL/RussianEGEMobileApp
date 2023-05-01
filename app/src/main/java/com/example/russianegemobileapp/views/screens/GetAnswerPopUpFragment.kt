package com.example.russianegemobileapp.views.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.russianegemobileapp.databinding.FragmentGetAnswerPopUpBinding
import com.example.russianegemobileapp.models.answer.AnswerFragment
import com.example.russianegemobileapp.models.answer.AnswerFragmentAdapter
import com.example.russianegemobileapp.models.theory.TheoryFragmentAdapter
import com.example.russianegemobileapp.views.MainActivity

class GetAnswerPopUpFragment : DialogFragment() {
    lateinit var binding:FragmentGetAnswerPopUpBinding
    val adapter = AnswerFragmentAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGetAnswerPopUpBinding.inflate(inflater,container,false)
        init()
        adapter.addAnswerFragment(AnswerFragment(1,10))
        adapter.addAnswerFragment(AnswerFragment(2,20))
        adapter.addAnswerFragment(AnswerFragment(3,30))
        adapter.addAnswerFragment(AnswerFragment(4,40))
        btnBinding()
        return binding.root
    }
    private fun init(){
        binding.apply{
            rcView?.layoutManager = LinearLayoutManager((activity as MainActivity).applicationContext)
            rcView?.adapter = adapter
        }
    }
    fun btnBinding(){
        binding.close?.setOnClickListener{
            dismiss()
        }
    }

}