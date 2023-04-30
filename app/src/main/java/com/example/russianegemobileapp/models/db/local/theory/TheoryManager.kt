package com.example.russianegemobileapp.models.db.local.theory

import android.content.Context
import androidx.room.Room
import com.example.russianegemobileapp.models.db.local.AppDatabaseManager

class TheoryManager {
    fun getDatabase(context: Context): TheoryDatabase {
        if (TheoryDatabase.INSTANCE ==null){
            synchronized(TheoryDatabase::class.java){
                if (TheoryDatabase.INSTANCE == null) {
                    TheoryDatabase.INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        TheoryDatabase::class.java, "theoryDB")
                        .createFromAsset("theory_database.db")
                        .build()
                }
            }
        }
        return TheoryDatabase.INSTANCE
    }
}

