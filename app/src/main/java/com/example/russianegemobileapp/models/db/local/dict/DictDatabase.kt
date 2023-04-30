package com.example.russianegemobileapp.models.db.local.dict

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Dict::class],version = 1)
abstract class DictDatabase :RoomDatabase(){
    abstract fun dictDao(): DictDao
    companion object {
        lateinit var INSTANCE: DictDatabase
    }
}
