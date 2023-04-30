package com.example.russianegemobileapp.models.db.local.dict

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.russianegemobileapp.models.Constants


@Entity(tableName = Constants.DICT_TABLE_NAME)
data class Dict(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var dictText:String,
)
