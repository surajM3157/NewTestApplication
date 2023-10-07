package com.example.newtestapplication.Roomdatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.newtestapplication.Modal.Meal

@Dao
interface ContactDAO {
    @Query("SELECT * FROM contact")
    fun getContact(): LiveData<List<Meal>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContact(meal: Meal)
    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateContact(meal: Meal)
    @Delete
    fun deleteContact(meal: Meal)

}