package com.example.russianegemobileapp.models.db.local.theory

import androidx.room.*
import com.example.russianegemobileapp.models.Constants


@Dao
interface TheoryDao {

    @Insert
    fun insertTheory(vararg theory: Theory)

    @Query("SELECT * FROM "+ Constants.THEORY_TABLE_NAME)
    fun getAllTheoryTexts(): List<Theory>


    @Delete
    fun deleteTheory(theory: Theory)

    @Query(
        "SELECT * FROM "+ Constants.THEORY_TABLE_NAME+
                " WHERE id=:id"
    )
    fun getOneTheoryText(id:Int): Theory
}