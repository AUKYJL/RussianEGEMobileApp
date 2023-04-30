package com.example.russianegemobileapp.views.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.russianegemobileapp.R
import com.example.russianegemobileapp.databinding.FragmentTheoryExModeBinding
import com.example.russianegemobileapp.models.Constants
import com.example.russianegemobileapp.models.db.TheoryTextToTitleAndDescTextMapper
import com.example.russianegemobileapp.models.db.local.theory.TheoryDao
import com.example.russianegemobileapp.models.db.local.theory.TheoryDatabase
import com.example.russianegemobileapp.models.theory.TheoryFragment
import com.example.russianegemobileapp.models.theory.TheoryFragmentAdapter
import com.example.russianegemobileapp.viewModels.TheoryExModeViewModel
import com.example.russianegemobileapp.viewModels.factories.TheoryExModeViewModelFactory
import com.example.russianegemobileapp.views.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class TheoryExModeFragment : Fragment() {
    val TEXTVIEW_WORD = "ExTextView"

    var amountTextView=0
    lateinit var binding: FragmentTheoryExModeBinding
    private val vm:TheoryExModeViewModel by viewModels{TheoryExModeViewModelFactory((activity as MainActivity).applicationContext)}
    val adapter = TheoryFragmentAdapter()

    private lateinit var theoryDao: TheoryDao



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTheoryExModeBinding.inflate(inflater, container, false)
        btnsBinding()
        //addTextViews()
        init()
        addObserver()
        val numberOfTask = arguments?.getInt("numberOfTask")
        //vm.createTheoryFragmentList(numberOfTask!!)

        val db = Room.databaseBuilder(
            (activity as MainActivity).applicationContext,
            TheoryDatabase::class.java, Constants.DB_THEORY_NAME
        )
            .createFromAsset(Constants.DB_THEORY_PATH)
            .build()
        theoryDao = db.theoryDao()


        return binding.root

    }

    private fun init(){
        binding.apply{
            rcView?.layoutManager = LinearLayoutManager((activity as MainActivity).applicationContext)
            rcView?.adapter = adapter
        }
    }

    override fun onStart() {
        super.onStart()
//        val title = "TITLETITLETITLETITLETITLETITLETITLETITLETITLETITLETITLETITLETITLETITLETITLETITLETITLETITLEITLETITLETITLETITLETITLEITLETITLETITLETITLETITLEITLETITLETITLETITLETITLEITLETITLETITLETITLETITLEITLETITLETITLETITLETITLEITLETITLETITLETITLETITLEITLETITLETITLETITLETITLEITLETITLETITLETITLETITLEITLETITLETITLETITLETITLE"
//        val desc = "DESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESCDESC"
//        adapter.addTheoryFragment(theoryFragment = TheoryFragment(title,desc))
//        adapter.addTheoryFragment(theoryFragment = TheoryFragment(title,desc))
//        adapter.addTheoryFragment(theoryFragment = TheoryFragment(title,desc))
//        adapter.addTheoryFragment(theoryFragment = TheoryFragment(title,desc))
//        adapter.addTheoryFragment(theoryFragment = TheoryFragment(title,desc))
//        adapter.addTheoryFragment(theoryFragment = TheoryFragment(title,desc))
//        adapter.addTheoryFragment(theoryFragment = TheoryFragment(title,desc))
//        adapter.addTheoryFragment(theoryFragment = TheoryFragment(title,desc))
//        adapter.addTheoryFragment(theoryFragment = TheoryFragment(title,desc))
//        adapter.addTheoryFragment(theoryFragment = TheoryFragment(title,desc))
//        adapter.addTheoryFragment(theoryFragment = TheoryFragment(title,desc))

        val numberOfTask = arguments?.getInt("numberOfTask")

        lifecycleScope.launch(Dispatchers.IO) {
            //theoryDao.insertTheory(Theory(0,"Первая задачка"))
            val theory = theoryDao.getOneTheoryText(numberOfTask!!)
            //Log.d("MyLog","${theories.size} dates")
            val textMapper = TheoryTextToTitleAndDescTextMapper()
            Log.d("MyLog","${theory.id} ${theory.theoryText}")
            val text = textMapper.execute(theory)
            val titles = text[0]
            val descs = text[1]
            Log.d("MyLog","${titles}")
            Log.d("MyLog","${descs}")
            withContext(Dispatchers.Main){
                for (i in 0 until titles.size){
                    adapter.addTheoryFragment(theoryFragment = TheoryFragment(titles[i],descs[i]))
                }


            }


        }

//        vm.loadExText(requireArguments().getInt("numberOfTask")-1)



    }
    fun addTextViews(){
        vm.amountOfTextView.observe(viewLifecycleOwner,{
            for (i in 0 until it){
                val tv = TextView((activity as MainActivity).applicationContext)
                tv.setText(i.toString())
                binding.scrollLayout?.addView(tv)
            }
        })

        vm.getAmountOfTextView(requireArguments().getInt("numberOfTask")-1)
    }
    fun addObserver(){
        vm.theoryFragmentList.observe(viewLifecycleOwner,Observer{
            lifecycleScope.launch(Dispatchers.IO){
                it.forEach {
                    adapter.addTheoryFragment(it)
                }
            }
        })
//        vm.titlesAndDescsText.observe(viewLifecycleOwner, Observer {
//            lifecycleScope.launch(Dispatchers.IO){
//
//
////                val titles = it.get(0)
////                val descs = it.get(1)
////
////                val arrayOfTitleTextView = arrayOfStringToTextView(titles)
////                val arrayOfDescsTextView = arrayOfStringToTextView(descs)
////
////                for (i in 0 until amountTextView){
////                    val tv =binding.scrollLayout?.getChildAt(i) as TextView
////                    if (i%2==0){
////                        tv.setText(arrayOfTitleTextView.removeAt(0).text)
////                    }else{
////                        tv.setText(arrayOfDescsTextView.removeAt(0).text)
////                    }
////                }
////                Log.e("MyLog","observe added")
//            }
//        })
    }

    fun arrayOfStringToTextView(array:ArrayList<String>):ArrayList<TextView>{
        val arrayOfTextView=ArrayList<TextView>()
        array.forEach {
            val tv = TextView((activity as MainActivity).applicationContext)
            tv.setText(it)
            arrayOfTextView.add(tv)
        }
        return arrayOfTextView

    }

    fun btnsBinding() {
        binding.backBtn?.setOnClickListener {
            (activity as MainActivity).navController.navigate(R.id.action_theoryExModeFragment_to_theoryModeFragment)
        }
    }

}