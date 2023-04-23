package com.example.russianegemobileapp.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.room.Room
import com.example.russianegemobileapp.R
import com.example.russianegemobileapp.databinding.ActivityMainBinding
import com.example.russianegemobileapp.models.db.TextToTitleAndDescTextMapper
import com.example.russianegemobileapp.models.db.local.TheoryDao
import com.example.russianegemobileapp.models.db.local.TheoryDatabase
import com.example.russianegemobileapp.models.db.local.TheoryManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController
    lateinit var binding : ActivityMainBinding
    lateinit var theoryDao:TheoryDao
    val tm = TheoryManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = Room.databaseBuilder(
            applicationContext,
            TheoryDatabase::class.java, "appDB")
            .createFromAsset("theory_database.db")
            .build()
        theoryDao = db.theoryDao()

        testDB()

        //Убирает статусбар
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        navController = Navigation.findNavController(this,R.id.nav_host)
    }
    private fun testDB(){

        lifecycleScope.launch(Dispatchers.IO) {
            //theoryDao.insertTheory(Theory(0,"Первая задачка"))
            val theory = theoryDao.getOneTheoryText(1)
            //Log.d("MyLog","${theories.size} dates")

            Log.d("MyLog","${theory.id} ${theory.theoryText}")


        }
    }
}