package com.example.russianegemobileapp.models.db.local.theory

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.russianegemobileapp.models.Constants


@Entity(tableName = Constants.THEORY_TABLE_NAME)
data class Theory(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var theoryText:String,
)
