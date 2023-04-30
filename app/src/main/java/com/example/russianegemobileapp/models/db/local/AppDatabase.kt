package com.example.russianegemobileapp.models.db.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.russianegemobileapp.models.db.local.dict.Dict
import com.example.russianegemobileapp.models.db.local.dict.DictDao
import com.example.russianegemobileapp.models.db.local.theory.Theory
import com.example.russianegemobileapp.models.db.local.theory.TheoryDao

@Database(entities = [Theory::class,Dict::class],version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun theoryDao(): TheoryDao
    abstract fun dictDao(): DictDao

//    companion object {
//        var INSTANCE:AppDatabase?=null
//    }
}