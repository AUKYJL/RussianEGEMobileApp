package com.example.russianegemobileapp.models.db.local

import androidx.room.*

@Dao
interface TheoryDao {
    @Insert
    fun insertTheory(vararg theory: Theory)

    @Query("SELECT * FROM Theory")
    fun getAllTheoryTexts(): List<Theory>


    @Delete
    fun deleteTheory(theory: Theory)

    @Query("""
        SELECT * FROM Theory
        WHERE id=:id
    """)
    fun getOneTheoryText(id:Int):Theory
}