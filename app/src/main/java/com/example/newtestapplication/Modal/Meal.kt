package com.example.newtestapplication.Modal

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact")
data class Meal(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo val idMeal: String,
    @ColumnInfo val strMeal: String,
    @ColumnInfo val strMealThumb: String
)