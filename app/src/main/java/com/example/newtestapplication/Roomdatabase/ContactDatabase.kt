package com.example.newtestapplication.Roomdatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newtestapplication.Modal.Meal


@Database(entities = [Meal::class], version = 1, exportSchema = false)
abstract class ContactDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDAO
}