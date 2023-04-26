package com.example.russianegemobileapp.models.db.local.theory

import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity
data class Theory(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var theoryText:String,
)
