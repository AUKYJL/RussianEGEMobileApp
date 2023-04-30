package com.example.russianegemobileapp.models.db.local.dict

import android.content.Context
import androidx.room.Room

class DictManager {

    fun getDatabase(context: Context): DictDatabase {
        if (DictDatabase.INSTANCE ==null){
            synchronized(DictDatabase::class.java){
                if (DictDatabase.INSTANCE == null) {
                    DictDatabase.INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        DictDatabase::class.java, "dictDB")
                        .createFromAsset("dict_database.db")
                        .build()
                }
            }
        }
        return DictDatabase.INSTANCE
    }
}

