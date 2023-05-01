package com.example.russianegemobileapp.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.room.Room
import com.example.russianegemobileapp.R
import com.example.russianegemobileapp.databinding.ActivityMainBinding
import com.example.russianegemobileapp.models.Constants
import com.example.russianegemobileapp.models.db.local.AppDatabase
import com.example.russianegemobileapp.models.db.local.dict.DictDao
import com.example.russianegemobileapp.models.db.local.dict.DictDatabase
import com.example.russianegemobileapp.models.db.local.theory.TheoryDao
import com.example.russianegemobileapp.models.db.local.theory.TheoryDatabase
import com.example.russianegemobileapp.models.db.local.theory.TheoryManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController
    lateinit var binding : ActivityMainBinding
    lateinit var db: AppDatabase
    lateinit var theoryDao: TheoryDao
    lateinit var theoryDao1: TheoryDao
    lateinit var theoryDao3: TheoryDao
    lateinit var dictDao: DictDao
    val tm = TheoryManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Log.d("MyLog",AppDatabase.INSTANCE.toString())

        val t = resources.getString(R.string.open_one_number_answer,123)
        Log.d("MyLog",t)

        //Log.d("MyLog",AppDatabase.INSTANCE.toString())



//        val db1 = Room.databaseBuilder(
//            applicationContext,
//            TheoryDatabase::class.java, DbConstants.DB_NAME
//        )
//            .createFromAsset(DbConstants.DB_PATH)
//            .build()
//        Log.d("MyLog",db1.toString())
//        theoryDao1 = db1.theoryDao()


        //Dependencies.init(applicationContext)


        val db3 = Room.databaseBuilder(
            applicationContext,
            TheoryDatabase::class.java, Constants.DB_THEORY_NAME
        )
            .createFromAsset(Constants.DB_THEORY_PATH)
            .build()
        theoryDao3 = db3.theoryDao()

        val db4 = Room.databaseBuilder(
            applicationContext,
            DictDatabase::class.java, Constants.DB_DICT_NAME
        )
            .createFromAsset(Constants.DB_DICT_PATH)
            .build()
        dictDao = db4.dictDao()

        testDB()

        //Убирает статусбар
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        navController = Navigation.findNavController(this,R.id.nav_host)
    }
    private fun testDB(){

        lifecycleScope.launch(Dispatchers.IO) {
            //theoryDao.insertTheory(Theory(0,"Первая задачка"))
            //db = AppDatabaseManager().getDatabase(applicationContext)
            //theoryDao = db.theoryDao()

            val theory3 = theoryDao3.getOneTheoryText(1)
            Log.d("MyLog",theory3.toString())

            val dict = dictDao.getParonymDictTexts()
            Log.d("MyLog",dict.toString())


            //val db2 = Dependencies.appDatabase
            //val theoryDao2 = db2.theoryDao()
            //Log.d("MyLog",theoryDao::class.java.toString())
            //Log.d("MyLog",theoryDao1::class.java.toString())
            //Log.d("MyLog",theoryDao2::class.java.toString())

            //val theory2 = theoryDao2.getAllTheoryTexts()
            //Log.d("MyLog",theory2.toString())
            //val theory1 = theoryDao1.getAllTheoryTexts()
            //Log.d("MyLog",theory1.toString())
            //val theory = theoryDao.getAllTheoryTexts()
            //Log.d("MyLog",theory.toString())
            //val theory = theoryDao.getOneTheoryText(1)
            //Log.d("MyLog","${theories.size} dates")
            //val dict = dictDao.getAll()
            //Log.d("MyLog","${theory.id} ${theory.theoryText}")
            //Log.d("MyLog","$dict")


        }
    }
}