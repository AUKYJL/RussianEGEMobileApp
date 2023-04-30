package com.example.russianegemobileapp.models.db.local.dict

import androidx.room.*
import com.example.russianegemobileapp.models.Constants

@Dao
interface DictDao {

    @Query("SELECT * FROM " + Constants.DICT_TABLE_NAME+
            " WHERE id=1")
    fun getOrthoepyDictTexts(): Dict


    @Query("SELECT * FROM "+ Constants.DICT_TABLE_NAME+
            " WHERE id=2")
    fun getParonymDictTexts(): Dict

    @Query("SELECT * FROM "+ Constants.DICT_TABLE_NAME)
    fun getAll():List<Dict>
}