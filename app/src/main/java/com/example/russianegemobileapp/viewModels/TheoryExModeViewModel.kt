package com.example.russianegemobileapp.viewModels

import android.content.Context
import android.util.Log
import android.window.OnBackInvokedCallback
import androidx.lifecycle.*
import androidx.room.Room
import com.example.russianegemobileapp.models.db.TheoryTextToTitleAndDescTextMapper
import com.example.russianegemobileapp.models.db.local.theory.TheoryDatabase
import com.example.russianegemobileapp.models.theory.TheoryFragment
import kotlinx.coroutines.*

class TheoryExModeViewModel(val context: Context) : ViewModel() {


    private var titlesAndDescsTextMutable = MutableLiveData<List<ArrayList<String>>>()
    private var amountOfTextViewMutable = MutableLiveData<Int>()
    private var theoryFragmentListMutable = MutableLiveData<ArrayList<TheoryFragment>>()
//    private var descsTextMutable = MutableLiveData<ArrayList<String>>()
//    private var titlesTextMutable = MutableLiveData<ArrayList<String>>()

    var theoryFragmentList: LiveData<ArrayList<TheoryFragment>> = theoryFragmentListMutable
    var titlesAndDescsText: LiveData<List<ArrayList<String>>> = titlesAndDescsTextMutable
    var amountOfTextView: LiveData<Int> = amountOfTextViewMutable
//    var descsText:LiveData<ArrayList<String>> = descsTextMutable
//    var titlesText:LiveData<ArrayList<String>> = titlesTextMutable

//    fun createTheoryFragmentList(numberOfTask: Int) {
//        runBlocking {
//            var texts = ArrayList<ArrayList<String>>()
//            val task = async { texts = loadExText(numberOfTask) }
//            runBlocking {
//                task.await()
//            }
//            Log.e("MyLog", "size ${texts.size}")
//            Log.e("MyLog", texts.toString())
//            val tempTheoryFragmentList = ArrayList<TheoryFragment>()
//            for (i in 0 until texts[0].size) {
//                val theoryFragment = TheoryFragment(texts[0][i], texts[1][i])
//                tempTheoryFragmentList.add(theoryFragment)
//            }
//            theoryFragmentListMutable.postValue(tempTheoryFragmentList)
//        }
//
//    }

    fun getAmountOfTextView(numberOfTask: Int) {
        Log.e("MyLog", "loadExText started")
        Log.e("MyLog", numberOfTask.toString())
        titlesAndDescsTextMutable.value = arrayListOf(
            arrayListOf("Заголовок1", "Еще заголовок 2"),
            arrayListOf("Какой-то текст для описания задачки все-такое", "Еще текст")
        )
        val textMapper = TheoryTextToTitleAndDescTextMapper()
        //запрос к дб и получение текста
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                val db = Room.databaseBuilder(
                    context,
                    TheoryDatabase::class.java, "theoryDB"
                )
                    .createFromAsset("theory_database.db")
                    .build()
                val theoryDao = db.theoryDao()

                val resultDeferred: Deferred<Int> = async {
                    val theory = theoryDao.getOneTheoryText(numberOfTask - 1)
                    val res = textMapper.execute(theory)
                    return@async res[0].size + res[1].size
                }

                amountOfTextViewMutable.postValue(resultDeferred.await())
                Log.e("MyLog", amountOfTextViewMutable.toString())
            }
        }
    }


//    suspend fun loadExText(numberOfTask: Int,callback: OnBackInvokedCallback): Nothing = withContext(Dispatchers.IO) {
//        Log.e("MyLog", "loadExText started")
//        Log.e("MyLog", numberOfTask.toString())
////        titlesAndDescsTextMutable.value = arrayListOf(
////            arrayListOf("Заголовок1", "Еще заголовок 2"),
////            arrayListOf("Какой-то текст для описания задачки все-такое", "Еще текст")
////        )
//        val res = ArrayList<ArrayList<String>>()
//        runBlocking {
//            Log.e("MyLog", "entered")
//            val task = async {
//                viewModelScope.launch(Dispatchers.IO) {
//                    val textMapper = TheoryTextToTitleAndDescTextMapper()
//                    //запрос к дб и получение текста
//                    val db = Room.databaseBuilder(
//                        context,
//                        TheoryDatabase::class.java, "theoryDB"
//                    )
//                        .createFromAsset("theory_database.db")
//                        .build()
//                    val theoryDao = db.theoryDao()
//
//                    val theory = theoryDao.getOneTheoryText(numberOfTask - 1)
//                    Log.e("MyLog", theory.theoryText)
//                    //titlesAndDescsTextMutable.value = textMapper.execute(theory)
//                    val titles = textMapper.execute(theory)[0]
//                    val descs = textMapper.execute(theory)[1]
//                    res.add(titles)
//                    res.add(descs)
//                }
//            }
//            runBlocking { task.await() }
//
//        }
//
//    }
}