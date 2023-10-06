package com.example.newtestapplication.Roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newtestapplication.Modal.Meal
@Database(entities = [Meal::class], version = 1, exportSchema = false)
abstract class ContactDatabase : RoomDatabase() {
    companion object {
        private var INSTANCE: ContactDatabase? = null
        fun getInstance(context: Context): ContactDatabase {
            if (INSTANCE == null) {
                synchronized(ContactDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ContactDatabase::class.java,
                        "contact_database"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
    abstract fun contactDao(): ContactDAO
}