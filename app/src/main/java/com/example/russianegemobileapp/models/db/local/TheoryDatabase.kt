package com.example.russianegemobileapp.models.db.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Theory::class],version = 1)
abstract class TheoryDatabase :RoomDatabase(){
    abstract fun theoryDao(): TheoryDao
    companion object {
        lateinit var INSTANCE: TheoryDatabase
    }
}
