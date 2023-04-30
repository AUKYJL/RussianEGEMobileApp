package com.example.russianegemobileapp.views.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.russianegemobileapp.R
import com.example.russianegemobileapp.databinding.FragmentDictInModeBinding
import com.example.russianegemobileapp.models.Constants
import com.example.russianegemobileapp.models.db.DictTextToTitleAndDescTextMapper
import com.example.russianegemobileapp.models.db.local.dict.DictDao
import com.example.russianegemobileapp.models.db.local.dict.DictDatabase
import com.example.russianegemobileapp.models.dict.DictFragment
import com.example.russianegemobileapp.models.dict.DictFragmentAdapter
import com.example.russianegemobileapp.views.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DictInModeFragment : Fragment() {
    lateinit var binding:FragmentDictInModeBinding

    val adapter = DictFragmentAdapter()

    private lateinit var dictDao: DictDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDictInModeBinding.inflate(inflater,container,false)
        init()
        val db = Room.databaseBuilder(
            (activity as MainActivity).applicationContext,
            DictDatabase::class.java, Constants.DB_DICT_NAME
        )
            .createFromAsset(Constants.DB_DICT_PATH)
            .build()
        dictDao = db.dictDao()


        btnsBinding()
        return binding.root
    }
    private fun init(){
        binding.apply{
            rcView?.layoutManager = LinearLayoutManager((activity as MainActivity).applicationContext)
            rcView?.adapter = adapter
        }
    }

    fun getParonymsText(){
        lifecycleScope.launch(Dispatchers.IO){
            val dictText = dictDao.getParonymDictTexts()
            Log.d("MyLog"," sss $dictText")
            val textMapper = DictTextToTitleAndDescTextMapper()
            val text = textMapper.execute(dictText)
            val titles = text[0]
            val descs = text[1]
            Log.d("MyLog","asd ${titles}")
            Log.d("MyLog","asd ${descs}")
            withContext(Dispatchers.Main){
                for (i in 0 until titles.size){
                    adapter.addDictFragment(dictFragment = DictFragment(titles[i],descs[i]))
                }
            }
        }
    }
    fun getOrthoepyText(){
        lifecycleScope.launch(Dispatchers.IO){
            val dictText = dictDao.getOrthoepyDictTexts()
            Log.d("MyLog"," sss $dictText")
            val textMapper = DictTextToTitleAndDescTextMapper()
            val text = textMapper.execute(dictText)
            val titles = text[0]
            val descs = text[1]
            Log.d("MyLog","asd ${titles}")
            Log.d("MyLog","asd ${descs}")
            withContext(Dispatchers.Main){
                for (i in 0 until titles.size){
                    adapter.addDictFragment(dictFragment = DictFragment(titles[i],descs[i]))
                }
            }
        }
    }

    override fun onStart() {
        val typeOfDict = arguments?.getString(Constants.BUNDLE_DICT_KEY)!!
        when (typeOfDict){
            Constants.BUNDLE_DICT_PUT_PARONYM -> getParonymsText()
            else-> getOrthoepyText()
        }
        super.onStart()


//        lifecycleScope.launch(Dispatchers.IO){
//            getParonymsText()
//        }
    }

    fun btnsBinding(){
        binding.backBtn?.setOnClickListener {
            (activity as MainActivity).navController.navigate(R.id.action_dictInModeFragment_to_dictModeFragment)
        }
    }

}