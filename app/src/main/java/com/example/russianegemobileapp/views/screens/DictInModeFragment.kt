package com.example.russianegemobileapp.views.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SearchView
import androidx.fragment.app.Fragment
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
import java.util.*

class DictInModeFragment : Fragment() {
    lateinit var binding: FragmentDictInModeBinding
    private lateinit var searchView: SearchView
    private lateinit var dictDao: DictDao

    val adapter = DictFragmentAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDictInModeBinding.inflate(inflater,container,false)
        val db = Room.databaseBuilder(
            (activity as MainActivity).applicationContext,
            DictDatabase::class.java, Constants.DB_DICT_NAME
        )
            .createFromAsset(Constants.DB_DICT_PATH)
            .build()
        dictDao = db.dictDao()

        searchView = binding.searchView!!
        searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.isIconified = true
                searchView.setQuery(query,false)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })

        val closeButtonId: Int = searchView.context.resources.getIdentifier("android:id/search_close_btn", null, null)
        val closeButton = searchView.findViewById(closeButtonId) as ImageView
        closeButton.setOnClickListener{
            searchView.isIconified = true
            searchView.clearFocus()
            adapter.dictFragmentList.clear()
            getData()
            true
        }


        init()
        btnsBinding()
        return binding.root
    }

    private fun filterList(query:String?){
        if (query!=null){
            val filteredList = ArrayList<DictFragment>()
            for (i in adapter.dictFragmentList){
                if (i.title.lowercase(Locale.ROOT).contains(query.lowercase())){
                    filteredList.add(i)
                }
            }
            if (!filteredList.isEmpty()){
                adapter.setFilteredList(filteredList)
            }
        }
    }

    fun getData(){
        val typeOfDict = arguments?.getString(Constants.BUNDLE_DICT_KEY)!!
        when (typeOfDict){
            Constants.BUNDLE_DICT_PUT_PARONYM -> getParonymsText()
            else-> getOrthoepyText()
        }
    }
    override fun onStart() {
        getData()
        super.onStart()
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



    private fun init(){
        binding.apply{
            rcView?.layoutManager = LinearLayoutManager((activity as MainActivity).applicationContext)
            rcView?.adapter = adapter
        }
    }

    fun btnsBinding(){
        binding.backBtn?.setOnClickListener {
            (activity as MainActivity).navController.navigate(R.id.action_dictInModeFragment_to_dictModeFragment)
        }
    }

}