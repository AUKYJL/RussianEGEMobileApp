package com.example.russianegemobileapp.views.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.russianegemobileapp.R
import com.example.russianegemobileapp.databinding.AchievementItemBinding
import com.example.russianegemobileapp.databinding.FragmentAchievModeBinding
import com.example.russianegemobileapp.models.achievement.AchievementFragment
import com.example.russianegemobileapp.models.achievement.AchievementFragmentAdapter
import com.example.russianegemobileapp.views.MainActivity


class AchievMode : Fragment() {
    lateinit var binding: FragmentAchievModeBinding
    val adapter = AchievementFragmentAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAchievModeBinding.inflate(inflater,container,false)
        binding.rcView
        init()
        adapter.addAchieveFragment(AchievementFragment("1 задачка","Впервые запустите приложение",50))
        adapter.addAchieveFragment(AchievementFragment("1 задачка","Впервые запустите приложениеВпервые запустите приложениеВпервые запустите приложениеВпервые запустите приложениеВпервые запустите приложениеВпервые запустите приложениеВпервые запустите приложениеВпервые запустите приложениеВпервые запустите приложениеВпервые запустите приложениеВпервые запустите приложениеВпервые запустите приложениеВпервые запустите приложениеВпервые запустите приложениеВпервые запустите приложение",20))
        adapter.addAchieveFragment(AchievementFragment("1 задачка","Впервые запустите приложение",0))
        adapter.addAchieveFragment(AchievementFragment("1 задачка","Впервые запустите приложение",80))
        adapter.addAchieveFragment(AchievementFragment("1 задачка","Впервые запустите приложение",100))
        btnsBinding()
        return binding.root
    }
    fun btnsBinding(){
        binding.backBtn?.setOnClickListener {
            (activity as MainActivity).navController.navigate(R.id.action_achievMode_to_mainFragment)
        }
    }
    private fun init(){
        binding.apply{
            rcView?.layoutManager = LinearLayoutManager((activity as MainActivity).applicationContext)
            rcView?.adapter = adapter
        }
    }


}